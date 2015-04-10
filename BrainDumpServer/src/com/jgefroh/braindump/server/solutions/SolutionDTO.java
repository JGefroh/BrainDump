package com.jgefroh.braindump.server.solutions;

import com.jgefroh.braindump.server.security.users.UserDTO;



public class SolutionDTO {
    
    private Integer id;
    private String text;
    private int kudoCount;
    private boolean isEditable;
    private boolean isDeletable;
    private boolean isKudoUsed;
    private UserDTO creator;

    
    
    public void setDeletable(boolean isDeletable) {
        this.isDeletable = isDeletable;
    }
    public boolean isDeletable() {
        return isDeletable;
    }
    
    
    public void setKudoUsed(boolean isKudoUsed) {
        this.isKudoUsed = isKudoUsed;
    }
    public boolean isKudoUsed() {
        return isKudoUsed;
    }
    
    
    public void setText(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }
    
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    
    
    public int getKudoCount() {
        return kudoCount;
    }
    public void setKudoCount(int kudoCount) {
        this.kudoCount = kudoCount;
    }
    
    
    public boolean isEditable() {
        return isEditable;
    }
    public void setEditable(boolean isEditable) {
        this.isEditable = isEditable;
    }
    
    
    public UserDTO getCreator() {
        return creator;
    }
    public void setCreator(UserDTO creator) {
        this.creator = creator;
    }
}
