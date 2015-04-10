package com.jgefroh.braindump.server.security.users;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(unique = true, nullable = false)
    @Basic(fetch = FetchType.LAZY)
    private String uuid;
    
    @Column(unique = true, nullable = false)
    private String username;

    
    public static User create(final String uuid, final String username) {
        User user = new User();
        user.setUUID(uuid);
        user.setUsername(username);
        return user;
    }

    public void updateUsername(String username) {
        setUsername(username);
    }

    public void loadSecrets() {
        getUUID();
    }
    
    
    
    public Integer getId() {
        return id;
    }
    private void setId(Integer id) {
        this.id = id;
    }

    
    public String getUUID() {
        return uuid;
    }
    private void setUUID(final String uuid) {
        this.uuid = uuid;
    }

    
    public String getUsername() {
        return username;
    }
    private void setUsername(String username) {
        this.username = username;
    }
}
