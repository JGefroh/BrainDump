package com.jgefroh.braindump.server.topics;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jgefroh.braindump.server.core.SecureEndpoint;
import com.jgefroh.braindump.server.security.User;


@RequestScoped
@Path("/topics")
public class TopicEndpoint extends SecureEndpoint {
    
    @Inject private TopicManager topicManager;
    @Inject private TopicMapper topicMapper;

    @GET
    public String sayHi() {
        System.out.println("HI");
        return "Hi!";
    }
    
        
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{organizationId}")
    public List<TopicSelectionDTO> getTopicsForOrganization(@PathParam("organizationId") final int organizationId) {
        User currentUser = getCurrentUser();
        List<Topic> topics = topicManager.getTopicsForOrganization(currentUser, organizationId);
        return topicMapper.mapToSelectionDTOs(topics);
    }
}