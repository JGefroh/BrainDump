package com.jgefroh.braindump.server.security;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;

import com.jgefroh.braindump.server.core.SecureEndpoint;
import com.jgefroh.braindump.server.security.users.User;


@RequestScoped
@Path("/security")
public class SecurityEndpoint extends SecureEndpoint {

    @Inject private SecurityManager securityManager;
    
    @GET
    @Path("/heartbeat")
    public User heartbeat() throws WebApplicationException {
        return getCurrentUser();
    }
    
    @PUT
    public User authenticate(final String uuid) {
        User user = securityManager.authenticate(uuid);
        saveCurrentUserIntoSession(user);
        return user;
    }
    
    @GET
    @Path("/uuid")
    public String generateUUID() {
        return UUIDGenerator.generate();
    }
    
    @GET
    @Path("/roles")
    public List<PredefinedRole> getRoles() {
        return Arrays.asList(PredefinedRole.values());
    }
}
