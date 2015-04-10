package com.jgefroh.braindump.server.topics;

import java.util.List;

import com.jgefroh.braindump.server.solutions.SolutionDTO;


public class TopicDTO {
    
    private Integer id;
    private String name;
    private Integer organizationId;
    private int viewCount;
    private boolean isEditable;
    private List<SolutionDTO> solutions;
    private int kudoCount;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    
    public Integer getOrganizationId() {
        return organizationId;
    }
    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }
    
    
    public boolean isEditable() {
        return isEditable;
    }
    public void setEditable(boolean isEditable) {
        this.isEditable = isEditable;
    }
    
    
    public List<SolutionDTO> getSolutions() {
        return solutions;
    }
    public void setSolutions(List<SolutionDTO> solutions) {
        this.solutions = solutions;
    }
    
    
    public int getViewCount() {
        return viewCount;
    }
    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }
    
    
    
    public int getKudoCount() {
        return kudoCount;
    }
    public void setKudoCount(int kudoCount) {
        this.kudoCount = kudoCount;
    }
}
