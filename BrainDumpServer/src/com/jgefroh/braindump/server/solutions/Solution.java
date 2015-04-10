package com.jgefroh.braindump.server.solutions;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.jgefroh.braindump.server.security.users.User;


@Entity
public class Solution {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @ManyToOne
    private User creator;
    
    private Integer topicId;
    
    private String text;
    
    @ElementCollection
    private List<Integer> kudos;
    
    @Transient
    private boolean isEditable;
    
    @Transient
    private boolean isKudoUsed;
    
    public static Solution create(final User creator, final String text) {
        Solution solution = new Solution();
        solution.setCreator(creator);
        solution.setText(text);
        return solution;
    }

    public void addKudo(User user) {
        if (!getKudos().contains(user.getId())) {
            getKudos().add(user.getId());
        }
    }

    public void removeKudo(User user) {
        getKudos().remove((Object) user.getId());
    }
    
    public void loadFull() {
        getKudos();
        getCreator();
    }

    public void update(final String text) {
        setText(text);
    }

    
    public User getCreator() {
        return creator;
    }
    private void setCreator(User creator) {
        this.creator = creator;
    }
    
    
    public Integer getId() {
        return id;
    }
    private void setId(Integer id) {
        this.id = id;
    }
    
    
    public List<Integer> getKudos() {
        if (kudos == null) {
            kudos = new ArrayList<Integer>();
        }
        return kudos;
    }
    void setKudos(List<Integer> kudos) {
        this.kudos = kudos;
    }
    
    
    public String getText() {
        return text;
    }
    private void setText(String text) {
        this.text = text;
    }
    
    
    public void setEditable(boolean isEditable) {
        this.isEditable = isEditable;
    }
    public boolean isEditable() {
        return isEditable;
    }
    
    
    
    public Integer getTopicId() {
        return topicId;
    }
    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }
    
    
    
    public boolean isKudoUsed() {
        return isKudoUsed;
    }
    public void setKudoUsed(boolean isKudoUsed) {
        this.isKudoUsed = isKudoUsed;
    }
}
