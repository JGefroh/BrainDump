package com.jgefroh.braindump.server.organizations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.jgefroh.braindump.server.security.Permission;
import com.jgefroh.braindump.server.security.users.User;

@Entity
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Permission> permissions;
    
    @ManyToOne
    private User user;
    
    
    
    public static Membership create(final User user, final List<Permission> permissions) {
        Membership membership = new Membership();
        membership.setUser(user);
        membership.setPermissions(permissions);
        return membership;
    }
    

    public void updatePermissions(final List<Permission> permissions) {
        setPermissions(permissions);
    }
    
    public boolean hasPermission(final Permission permission) {
        for (Permission membershipPermission : getPermissions()) {
            if (membershipPermission.equals(permission)) {
                return true;
            }
        }
        return false;
    }

    public boolean belongsTo(int userId) {
        if (user.getId().equals(userId)) {
            return true;
        }
        return false;
    }
    
    public void loadFull() {
        getPermissions();
        getUser();
    }

    
    
    public Integer getId() {
        return id;
    }
    private void setId(Integer id) {
        this.id = id;
    }
    
    
    public List<Permission> getPermissions() {
        if (permissions == null) {
            permissions = new ArrayList<Permission>();
        }
        return permissions;
    }
    private void setPermissions(final List<Permission> permissions) {
        this.permissions = permissions;
    }
    
    
    public User getUser() {
        return user;
    }
    private void setUser(User user) {
        this.user = user;
    }
}
