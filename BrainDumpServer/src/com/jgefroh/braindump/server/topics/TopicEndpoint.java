package com.jgefroh.braindump.server.topics;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jgefroh.braindump.server.core.SecureEndpoint;
import com.jgefroh.braindump.server.security.users.User;
import com.jgefroh.braindump.server.solutions.SolutionDTO;
import com.jgefroh.braindump.server.solutions.SolutionMapper;


@RequestScoped
@Path("/topics")
public class TopicEndpoint extends SecureEndpoint {
    
    @Inject private TopicManager topicManager;
    @Inject private TopicMapper topicMapper;
    @Inject private SolutionMapper solutionMapper;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{topicId}")
    public TopicDTO getTopic(@PathParam("topicId") final int topicId) {
        User currentUser = getCurrentUser();
        Topic topic = topicManager.viewTopic(currentUser, topicId);
        return topicMapper.mapFull(topic);
    }
        
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/organizations/{organizationId}")
    public List<TopicSelectionDTO> getTopicsForOrganization(@PathParam("organizationId") final int organizationId) {
        User currentUser = getCurrentUser();
        List<Topic> topics = topicManager.getTopicsForOrganization(currentUser, organizationId);
        return topicMapper.mapToSelectionDTOs(topics);
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public TopicDTO saveTopic(final TopicDTO topic) {
        User currentUser = getCurrentUser();
        return topicMapper.mapFull(topicManager.saveTopic(currentUser, topic));
    }
    
    @DELETE
    @Path("/{topicId}")
    public void deleteTopic(@PathParam("topicId") final Integer id) {
        User currentUser = getCurrentUser();
        topicManager.deleteTopic(currentUser, id);
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{topicId}/solutions")
    public SolutionDTO addSolution(@PathParam("topicId") final int topicId, final String solution) {
        User currentUser = getCurrentUser();
        return solutionMapper.map(topicManager.addSolution(currentUser, topicId, solution));
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/solutions")
    public SolutionDTO addSolution(final SolutionDTO solution) {
        User currentUser = getCurrentUser();
        return solutionMapper.map(topicManager.saveSolution(currentUser, solution));
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/solutions/{solutionId}/kudo")
    public SolutionDTO kudoSolution(@PathParam("solutionId") final int solutionId) {
        User currentUser = getCurrentUser();
        return solutionMapper.map(topicManager.kudoSolution(currentUser, solutionId));
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/solutions/{solutionId}/unkudo")
    public SolutionDTO unkudoSolution(@PathParam("solutionId") final int solutionId) {
        User currentUser = getCurrentUser();
        return solutionMapper.map(topicManager.unkudoSolution(currentUser, solutionId));
    }
    
    @DELETE
    @Path("/solutions/{solutionId}")
    public void deleteSolution(@PathParam("solutionId") final Integer id) {
        User currentUser = getCurrentUser();
        topicManager.deleteSolution(currentUser, id);
    }

}