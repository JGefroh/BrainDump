package com.jgefroh.braindump.server.organizations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.jgefroh.braindump.server.security.Permission;
import com.jgefroh.braindump.server.security.Role;
import com.jgefroh.braindump.server.security.users.User;

@Entity
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Membership> memberships;
    
    public static Organization create(final String name, final User owner) {
        Organization org = new Organization();
        org.setName(name);
        org.addOwner(owner);
        return org;
    }
    
    private void addOwner(final User user) {
        getMemberships().add(Membership.create(user, Role.getPermissionsForOrganizationOwner()));
    }

    public boolean hasUserWithPermission(int userId, Permission permission) {
        for (Membership membership : getMemberships()) {
            if (membership.belongsTo(userId) && membership.hasPermission(permission)) {
                return true;
            }
        }
        return false;
    }

    public void updateName(final String name) {
        setName(name);
    }


    public void addMembership(Membership membership) {
        if (!getMemberships().contains(membership)) {
            getMemberships().add(membership);
        }
    }
    
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public List<Membership> getMemberships() {
        if (this.memberships == null ) {
            this.memberships = new ArrayList<Membership>();
        }
        return memberships;
    }
}
