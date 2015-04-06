package com.jgefroh.braindump.server.security;

import javax.ejb.Stateless;
import javax.inject.Inject;


@Stateless
public class SecurityManager {

    @Inject private UserDAO userDAO;
    
    public User authenticate(final String uuid) {
        User user = userDAO.getUserWithUUID(uuid);
        if (user == null) {
            user = userDAO.update(User.create(uuid));
        }
        return user;
    }
    
}
