package com.group25.unibar.models;

public class BarInfo {

    private String id;
    private String description;
    private String barName;

    private int thumbnail;

    public BarInfo (String id, String description, String barName, int thumbnail){
        this.id = id;
        this.description = description;
        this.barName = barName;
        this.thumbnail = thumbnail;
    }

    /// Getter and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBarName() {
        return barName;
    }

    public void setBarName(String name) {
        this.barName = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

}
