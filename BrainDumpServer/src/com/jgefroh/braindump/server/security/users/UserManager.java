package com.jgefroh.braindump.server.security.users;

import javax.ejb.Stateless;
import javax.inject.Inject;


@Stateless
public class UserManager {

    @Inject private UserDAO userDAO;
    
    public User getUser(final int userId) {
        return userDAO.get(User.class, userId);
    }

    public User saveUser(User user, UserDTO userToSave) {
        User userEntity = getUser(userToSave.getId());
        if (userEntity.getId().equals(user.getId())) {
            userEntity.updateUsername(userToSave.getUsername());
        }
        return userEntity;
    }
    
}
