package com.jgefroh.braindump.server.security.users;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;


@Stateless
public class UserMapper {

    public UserDTO map(final User entity) {
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        return dto;
    }
}
