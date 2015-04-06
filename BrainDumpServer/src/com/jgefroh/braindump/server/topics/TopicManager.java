package com.jgefroh.braindump.server.topics;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.jgefroh.braindump.server.organizations.OrganizationManager;
import com.jgefroh.braindump.server.security.Permission;
import com.jgefroh.braindump.server.security.User;

@Stateless
public class TopicManager {
    
    @Inject private Logger logger;
    @Inject private TopicDAO topicDAO;
    @Inject private OrganizationManager organizationManager;
    
    public List<Topic> getTopicsForOrganization(final User currentUser, final int organizationId) {
        organizationManager.authorize(currentUser, organizationId, Permission.VIEW_TOPICS);
        List<Topic> topics = topicDAO.getTopicsForOrganization(organizationId);
        for (Topic topic : topics) {
            topic.loadSelection();
        }
        return topics;
    }
}
