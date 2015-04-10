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
                                Permission.MODERATE_TOPICS,
                                Permission.MODERATE_SOLUTIONS,
                                Permission.EDIT_OWN_SOLUTION,
                                Permission.VIEW_TOPICS);
    }

    public static List<Permission> getPermissionsForOrganizationAdmin() {
        return Arrays.asList(Permission.ADMINISTRATE_ORGANIZATION, 
                                Permission.EDIT_OWN_TOPIC, 
                                Permission.MODERATE_TOPICS,
                                Permission.MODERATE_SOLUTIONS,
                                Permission.EDIT_OWN_SOLUTION,
                                Permission.VIEW_TOPICS);
    }
    
    public static List<Permission> getPermissionsForOrganizationMember() {
        return Arrays.asList(Permission.EDIT_OWN_TOPIC, 
                             Permission.VIEW_TOPICS,
                             Permission.EDIT_OWN_SOLUTION);
    }
}
