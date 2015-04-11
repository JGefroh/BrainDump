package com.jgefroh.braindump.server.organizations;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.jgefroh.braindump.server.security.Permission;
import com.jgefroh.braindump.server.security.PredefinedRole;
import com.jgefroh.braindump.server.security.Role;
import com.jgefroh.braindump.server.security.users.User;
import com.jgefroh.braindump.server.security.users.UserDAO;
import com.jgefroh.braindump.server.solutions.Solution;
import com.jgefroh.braindump.server.topics.Topic;

@Stateless
public class OrganizationManager {

    @Inject private UserDAO userDAO;
    @Inject private OrganizationDAO organizationDAO;
    
    public Organization authorize(final User user, final int organizationId, final Permission permission) {
        if (user == null) {
            throw new IllegalArgumentException("User not logged in.");
        }
        if (permission == null) {
            throw new IllegalArgumentException("Action not specified.");
        }
        
        Organization organization = organizationDAO.get(Organization.class, organizationId);
        if (organization == null) {
            throw new IllegalArgumentException("Organization doesn't exist.");
        }
        if (!organization.hasUserWithPermission(user.getId(), permission)) {
            throw new IllegalArgumentException("User doesn't have permission.");
        }
        
        return organization;
    }
    
    private void populateOrganizationMetadata(final User user, final Organization organization) {
        if (organization.hasUserWithPermission(user.getId(), Permission.ADMINISTRATE_ORGANIZATION)) {
            organization.setEditable(true);
        }
    }
    
    public List<Organization> getOrganizationsFor(final User user) {
        List<Organization> organizations = organizationDAO.getOrganizationsForUserWithPermission(user.getId(), Permission.VIEW_TOPICS);
        for (Organization organization : organizations) {
            populateOrganizationMetadata(user, organization);
        }
        return organizations;
    }

    public Organization saveOrganization(final User user, final OrganizationDTO dto) {
        if (isNew(dto)) {
            Organization organization = Organization.create(dto.getName(), user);
            organization = organizationDAO.update(organization);
            return organization;
        }
        else {
            Organization organization = authorize(user, dto.getId(), Permission.ADMINISTRATE_ORGANIZATION);            
            organization.updateName(dto.getName());
            populateOrganizationMetadata(user, organization);
            return organization;
        }
    }
    
    private boolean isNew(final OrganizationDTO dto) {
        return dto.getId() == null;
    }
    
    public List<Membership> getMemberships(final User user, final int organizationId) {
        Organization organization = authorize(user, organizationId, Permission.ADMINISTRATE_ORGANIZATION);
        List<Membership> memberships = organization.getMemberships();

        for (Membership membership : memberships == null ? Collections.<Membership>emptyList() : memberships) {
            membership.loadFull();
        }
        
        return memberships;
    }

    public Membership addMemberAs(final User user, final int userToAddId, final int organizationId, final PredefinedRole role) {
        Organization organization = authorize(user, organizationId, Permission.ADMINISTRATE_ORGANIZATION);
        User userToAdd = userDAO.get(User.class, userToAddId);
        if (userToAdd == null) {
            throw new IllegalArgumentException("User does not exist.");
        }
        
        Membership membership = organizationDAO.getMembershipFor(userToAddId, organizationId);
        if (membership == null) {
            membership = Membership.create(userToAdd, null);
        }

        switch (role) {
            case ADMINISTRATOR:
                membership.updatePermissions(Role.getPermissionsForOrganizationAdmin());
                break;
            case MEMBER:
                membership.updatePermissions(Role.getPermissionsForOrganizationMember());
                break;
            case OWNER:
                membership.updatePermissions(Role.getPermissionsForOrganizationOwner());
                break;
            default:
                throw new IllegalArgumentException("Unknown role.");
        }
        membership = organizationDAO.update(membership);
        organization.addMembership(membership);
        organizationDAO.update(organization);
        membership.loadFull();
        return membership;
    }

    public void removeMemberFrom(final User currentUser, final int userToRemoveId, final Integer organizationId) {
        authorize(currentUser, organizationId, Permission.ADMINISTRATE_ORGANIZATION);
        Membership membership = organizationDAO.getMembershipFor(userToRemoveId, organizationId);
        if (membership != null) {
            organizationDAO.delete(Membership.class, membership.getId());
        }
    }
}
