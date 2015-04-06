package com.jgefroh.braindump.server.security;

import java.util.Arrays;
import java.util.List;

/**
 * @author Joseph Gefroh
 */
public class Role {

    public static List<Permission> getPermissionsForOrganizationOwner() {
        return Arrays.asList(Permission.ADMINISTRATE_ORGANIZATION, 
                                Permission.EDIT_OWN_TOPIC, 
                                Permission.EDIT_OTHER_TOPIC,
                                Permission.VIEW_TOPICS);
    }

    public static List<Permission> getPermissionsForOrganizationAdmin() {
        return Arrays.asList(Permission.ADMINISTRATE_ORGANIZATION, 
                                Permission.EDIT_OWN_TOPIC, 
                                Permission.EDIT_OTHER_TOPIC,
                                Permission.VIEW_TOPICS);
    }
    
    public static List<Permission> getOrganizationMemberRole() {
        return Arrays.asList(Permission.EDIT_OWN_TOPIC, 
                             Permission.VIEW_TOPICS);
    }
}
