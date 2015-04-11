package com.jgefroh.braindump.server.organizations;


public class OrganizationDTO {

    private Integer id;
    private String name;
    private boolean isDeletable;
    private boolean isEditable;
    
    
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
    
    public boolean isDeletable() {
        return isDeletable;
    }
    public void setDeletable(boolean isDeletable) {
        this.isDeletable = isDeletable;
    }
    
    public boolean isEditable() {
        return isEditable;
    }
    public void setEditable(boolean isEditable) {
        this.isEditable = isEditable;
    }
}
