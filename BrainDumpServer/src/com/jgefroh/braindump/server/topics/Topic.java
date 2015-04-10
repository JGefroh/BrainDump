package com.jgefroh.braindump.server.topics;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.jgefroh.braindump.server.organizations.Organization;
import com.jgefroh.braindump.server.security.users.User;
import com.jgefroh.braindump.server.solutions.Solution;


@Entity
public class Topic {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @ManyToOne
    private Organization organization;
    
    @ManyToOne
    private User creator;
    
    private String name;
    private String description;
    private List<String> tags;
    
    private int viewCount;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Solution> solutions;
    
    @ElementCollection
    private List<Integer> kudos;
    
    @Transient
    private boolean isEditable;


    
    public static Topic create(String name, Organization organization, User user) {
        Topic topic = new Topic();
        topic.setName(name);
        topic.setOrganization(organization);
        topic.setCreator(user);
        return topic;
    }
    
    
    public void loadSelection() {
        getTags();
        getSolutions();
        getKudos();
    }

    public void loadFull() {
        getTags();
        getSolutions();
        getKudos();
        getOrganization();
        getCreator();
    }

    public void updateName(String name) {
        setName(name);
    }

    public void incrementViewCount() {
        this.viewCount++;
    }

    public Solution addSolution(final User solutionCreator, final String solutionText) {
        Solution solution = Solution.create(solutionCreator, solutionText);
        solution.setTopicId(getId());
        getSolutions().add(solution);
        return solution;
    }
    
    
    
    public User getCreator() {
        return creator;
    }
    private void setCreator(User creator) {
        this.creator = creator;
    }
    
    public String getDescription() {
        return description;
    }
    
    public Integer getId() {
        return id;
    }
    
    public List<Integer> getKudos() {
        if (kudos == null) {
            kudos = new ArrayList<Integer>();
        }
        return kudos;
    }
    
    public String getName() {
        return name;
    }
    private void setName(final String name) {
        this.name = name;
    }
    
    public Organization getOrganization() {
        return organization;
    }
    private void setOrganization(Organization organization) {
        this.organization = organization;
    }
    
    
    public List<Solution> getSolutions() {
        if (solutions == null) {
            solutions = new ArrayList<Solution>();
        }
        return solutions;
    }
    
    public List<String> getTags() {
        return tags;
    }
    
    public int getViewCount() {
        return viewCount;
    }
    
    
    public boolean isEditable() {
        return isEditable;
    }
    public void setEditable(boolean isEditable) {
        this.isEditable = isEditable;
    }
}
