package com.jgefroh.braindump.server.security;


public enum Permission {
    EDIT_OWN_SOLUTION,
    MODERATE_SOLUTIONS,
    ADMINISTRATE_ORGANIZATION, 
    MODERATE_TOPICS, 
    EDIT_OWN_TOPIC,
    VIEW_TOPICS,
    
    
    //Outdated - Delete later
    @Deprecated
    EDIT_OTHER_TOPIC,
    ;
    
    
}
