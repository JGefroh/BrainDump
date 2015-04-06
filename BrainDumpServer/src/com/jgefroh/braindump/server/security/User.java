package com.jgefroh.braindump.server.security;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String uuid;
    private String username;

    
    public static User create(final String uuid) {
        User user = new User();
        user.setUUID(uuid);
        return user;
    }
    
    public Integer getId() {
        return id;
    }
    
    public String getUUID() {
        return uuid;
    }
    private void setUUID(final String uuid) {
        this.uuid = uuid;
    }
    
}
