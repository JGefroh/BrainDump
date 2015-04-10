package com.jgefroh.braindump.server.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response.Status;

import com.jgefroh.braindump.server.security.users.User;


public abstract class SecureEndpoint {

    @Context private HttpServletRequest request;
    
    private static final String SESSION_USER = "CURRENT_USER";
    
    protected User getCurrentUser() {
        HttpSession session = request.getSession(false);
        if (session == null) {
            throw new WebApplicationException(Status.UNAUTHORIZED);
        }
        
        Object currentUserAsObject = session.getAttribute(SESSION_USER);
        if (currentUserAsObject == null) {
            throw new WebApplicationException(Status.UNAUTHORIZED);
        }
        
        return (User) currentUserAsObject;
    }
    
    protected void saveCurrentUserIntoSession(final User user) {
        HttpSession session = request.getSession();
        session.setAttribute(SESSION_USER, user);
    }
}
