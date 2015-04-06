package com.jgefroh.braindump.server.topics;


public class TopicSelectionDTO {
    private int id;
    private String name;
    private String description;
    private int viewCount;
    private int solutionCount;
    private int kudoCount;
    
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public int getKudoCount() {
        return kudoCount;
    }
    public void setKudoCount(int kudoCount) {
        this.kudoCount = kudoCount;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public int getSolutionCount() {
        return solutionCount;
    }
    public void setSolutionCount(int solutionCount) {
        this.solutionCount = solutionCount;
    }
    
    public int getViewCount() {
        return viewCount;
    }
    
    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }
}
