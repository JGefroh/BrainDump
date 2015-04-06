package com.jgefroh.braindump.server.organizations;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jgefroh.braindump.server.core.SecureEndpoint;
import com.jgefroh.braindump.server.security.User;


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
}