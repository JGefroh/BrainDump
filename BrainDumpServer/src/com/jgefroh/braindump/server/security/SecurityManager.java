package com.jgefroh.braindump.server.security;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.jgefroh.braindump.server.security.users.User;
import com.jgefroh.braindump.server.security.users.UserDAO;


@Stateless
public class SecurityManager {

    @Inject private UserDAO userDAO;
    
    public User authenticate(final String uuid) {
        User user = userDAO.getUserWithUUID(uuid);
        if (user == null) {
            user = userDAO.update(User.create(uuid, "New User " + UUIDGenerator.generate().substring(0, 8)));
        }
        user.loadSecrets();
        return user;
    }
    
}
