package com.jgefroh.braindump.server.organizations;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.jgefroh.braindump.server.core.SecureEndpoint;
import com.jgefroh.braindump.server.security.PredefinedRole;
import com.jgefroh.braindump.server.security.users.User;


@RequestScoped
@Path("/organizations")
public class OrganizationEndpoint extends SecureEndpoint {
    
    @Inject private OrganizationManager organizationManager;
    @Inject private OrganizationMapper organizationMapper;

        
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Organization> getOrganizationsForCurrentUser() {
        User currentUser = getCurrentUser();
        return organizationManager.getOrganizationsFor(currentUser);
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public OrganizationDTO saveOrganization(final OrganizationDTO dto) {
        User currentUser = getCurrentUser();
        return organizationMapper.map(organizationManager.saveOrganization(currentUser, dto));
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{organizationId}/memberships")
    public List<Membership> getMembershipsForOrganization(@PathParam("organizationId") final Integer organizationId) {
        User currentUser = getCurrentUser();
        return organizationManager.getMemberships(currentUser, organizationId);
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{organizationId}/memberships")
    public Membership addMemberAs(@QueryParam("userId") final Integer userToAddId, 
                                  @PathParam("organizationId") final Integer organizationId, 
                                  @QueryParam("role") final PredefinedRole role) {
        User currentUser = getCurrentUser();
        return organizationManager.addMemberAs(currentUser, userToAddId, organizationId, role);
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{organizationId}/memberships")
    public void removeMember(@QueryParam("userId") final Integer userToRemoveId, 
                             @PathParam("organizationId") final Integer organizationId) {
        User currentUser = getCurrentUser();
        organizationManager.removeMemberFrom(currentUser, userToRemoveId, organizationId);
    }
}