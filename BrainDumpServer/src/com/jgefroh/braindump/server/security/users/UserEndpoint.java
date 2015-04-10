package com.jgefroh.braindump.server.security.users;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jgefroh.braindump.server.core.SecureEndpoint;
import com.jgefroh.braindump.server.security.SecurityManager;
import com.jgefroh.braindump.server.security.UUIDGenerator;


@RequestScoped
@Path("/users")
public class UserEndpoint extends SecureEndpoint {

    @Inject private SecurityManager securityManager;
    @Inject private UserManager userManager;
    @Inject private UserMapper userMapper;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO getSelf() {
        User user = getCurrentUser();
        return userMapper.map(userManager.getUser(user.getId()));
    }
    
    @PUT
    public UserDTO authenticate(final UserDTO userToSave) {
        User user = getCurrentUser();
        return userMapper.map(userManager.saveUser(user, userToSave));
    }
}
