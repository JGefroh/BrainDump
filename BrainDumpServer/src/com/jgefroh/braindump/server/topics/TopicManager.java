package com.jgefroh.braindump.server.topics;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.jgefroh.braindump.server.organizations.Organization;
import com.jgefroh.braindump.server.organizations.OrganizationManager;
import com.jgefroh.braindump.server.security.Permission;
import com.jgefroh.braindump.server.security.users.User;
import com.jgefroh.braindump.server.solutions.Solution;
import com.jgefroh.braindump.server.solutions.SolutionDTO;

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
    
    private Topic getTopic(final User user, final int topicId) {
        if (user == null) {
            throw new IllegalArgumentException("User not logged in.");
        }
        
        Topic topic = topicDAO.get(Topic.class, topicId);
        if (topic == null) {
            throw new IllegalArgumentException("Topic doesn't exist.");
        }
        
        if (topic.getOrganization().hasUserWithPermission(user.getId(), Permission.VIEW_TOPICS)) {
            return topic;
        }
        
        throw new IllegalArgumentException("Unauthorized.");
    }
    
    public Topic viewTopic(final User user, final int topicId) {
        Topic topic = getTopic(user, topicId);
        topic.loadFull();
        checkSolutions(user, topic);
        checkIsEditable(user, topic);
        topic.incrementViewCount();
        return topic;
    }
    
    public Topic saveTopic(final User user, final TopicDTO dto) {
        if (isNew(dto)) {
            Organization organization = organizationManager.authorize(user, dto.getOrganizationId(), Permission.EDIT_OWN_TOPIC);
            Topic topic = Topic.create(dto.getName(), organization, user);
            topic = topicDAO.update(topic);
            topic.loadFull();
            checkSolutions(user, topic);
            checkIsEditable(user, topic);
            return topic;
        }
        else {
            Topic topic = authorizeEditTopic(user, dto.getId());
            topic.updateName(dto.getName());
            topic.loadFull();
            checkSolutions(user, topic);
            checkIsEditable(user, topic);
            return topic;
        }
    }
    
    private Topic authorizeEditTopic(final User user, final int topicId) {
        Topic topic = getTopic(user, topicId);
        if (checkIsEditable(user, topic)) {
            return topic;
        }
        else {
            throw new IllegalArgumentException("Unauthorized.");
        }
    }
    
    private boolean checkIsEditable(final User user, final Topic topic) {
        if ((isCreator(user, topic) && topic.getOrganization().hasUserWithPermission(user.getId(), Permission.EDIT_OWN_TOPIC))
                || topic.getOrganization().hasUserWithPermission(user.getId(), Permission.MODERATE_TOPICS)) {
            topic.setEditable(true);
            return true;
        }
        return false;
    }
    
    private boolean isCreator(final User user, final Topic topic) {
        return topic.getCreator().getId().equals(user.getId());
    }
    
    private boolean isNew(final TopicDTO dto) {
        return dto.getId() == null;
    }
    
    
    private void checkSolutions(final User user, final Topic topic) {
        for (Solution solution : topic.getSolutions()) {
            populateSolutionMetadata(user, topic, solution);
        }
    }

    public void deleteTopic(final User user, final int topicId) {
        Topic topic = authorizeEditTopic(user, topicId);
        topicDAO.delete(Topic.class, topicId);
    }
    
    
    private boolean isCreator(final User user, final Solution solution) {
        return solution.getCreator().getId().equals(user.getId());
    }
    

    public Solution addSolution(final User user, final int topicId, final SolutionDTO solution) {
        Topic topic = authorizeAddSolution(user, topicId);
        Solution savedSolution = topic.addSolution(user, solution.getText());
        populateSolutionMetadata(user, topic, savedSolution);
        return savedSolution;
    }
    
    private Topic authorizeAddSolution(final User user, final int topicId) {
        Topic topic = getTopic(user, topicId);
        if (!topic.getOrganization().hasUserWithPermission(user.getId(), Permission.EDIT_OWN_SOLUTION)) {
            throw new IllegalArgumentException("Unauthorized.");
        }
        return topic;
    }

    public Solution kudoSolution(final User user, final int solutionId) {
        Solution solution = topicDAO.get(Solution.class, solutionId);
        Topic topic = getTopic(user, solution.getTopicId());
        solution.loadFull();
        solution.addKudo(user);
        populateSolutionMetadata(user, topic, solution);
        return solution;
    }

    public Solution unkudoSolution(final User user, final int solutionId) {
        Solution solution = topicDAO.get(Solution.class, solutionId);
        Topic topic = getTopic(user, solution.getTopicId());
        solution.loadFull();
        solution.removeKudo(user);
        populateSolutionMetadata(user, topic, solution);
        return solution;
    }
    
    private void populateSolutionMetadata(final User user, final Topic topic, final Solution solution) {
        if (topic.getOrganization().hasUserWithPermission(user.getId(), Permission.MODERATE_SOLUTIONS)
                || (isCreator(user, solution) && topic.getOrganization().hasUserWithPermission(user.getId(), Permission.EDIT_OWN_SOLUTION))) {
            solution.setEditable(true);
        }
        
        if (solution.getKudos().contains(user.getId())) {
            solution.setKudoUsed(true);
        }
    }

    public Solution saveSolution(User user, SolutionDTO solutionToSave) {
        Solution solution = topicDAO.get(Solution.class, solutionToSave.getId());
        Topic topic = getTopic(user, solution.getTopicId());
        populateSolutionMetadata(user, topic, solution);
        if (solution.isEditable()) {
            solution.update(solutionToSave.getText());
        }
        else {
            throw new IllegalArgumentException("Unauthorized.");
        }
        return solution;
    }

    public void deleteSolution(final User user, int id) {
        Solution solution = topicDAO.get(Solution.class, id);
        Topic parentTopic = topicDAO.get(Topic.class, solution.getTopicId());
        populateSolutionMetadata(user, parentTopic, solution);
        if (solution.isEditable()) {
            topicDAO.delete(Solution.class, id);
        }
        else {
            throw new IllegalArgumentException("Unauthorized");
        }
    }
}
