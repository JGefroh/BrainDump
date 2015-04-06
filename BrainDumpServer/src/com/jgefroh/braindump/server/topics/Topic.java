package com.jgefroh.braindump.server.topics;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.jgefroh.braindump.server.security.User;
import com.jgefroh.braindump.server.solutions.Kudo;
import com.jgefroh.braindump.server.solutions.Solution;


@Entity
public class Topic {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private User creator;
    
    private String name;
    private String description;
    private List<String> tags;
    
    private int views;
    
    private List<Solution> solutions;
    private List<Kudo> kudos;
    
    
    public void loadSelection() {
        getTags();
        getSolutions();
        getKudos();
    }
    
    
    
    public User getCreator() {
        return creator;
    }
    public String getDescription() {
        return description;
    }
    public Integer getId() {
        return id;
    }
    public List<Kudo> getKudos() {
        return kudos;
    }
    public String getName() {
        return name;
    }
    public List<Solution> getSolutions() {
        return solutions;
    }
    public List<String> getTags() {
        return tags;
    }
    public int getViews() {
        return views;
    }
}
