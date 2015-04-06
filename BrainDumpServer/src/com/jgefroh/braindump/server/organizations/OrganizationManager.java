package com.jgefroh.braindump.server.organizations;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.jgefroh.braindump.server.security.Permission;
import com.jgefroh.braindump.server.security.User;

@Stateless
public class OrganizationManager {
    
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
    
    public List<Organization> getOrganizationsFor(final User user) {
        List<Organization> organizations = organizationDAO.getOrganizationsForUserWithPermission(user.getId(), Permission.VIEW_TOPICS);
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
            return organization;
        }
    }
    
    private boolean isNew(final OrganizationDTO dto) {
        return dto.getId() == null;
    }
}
