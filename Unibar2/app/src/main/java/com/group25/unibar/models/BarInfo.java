package com.group25.unibar.models;

import java.util.ArrayList;

public class BarInfo {

    private String id;
    private String description;
    private String barName;
    private ArrayList<Review> reviews;
    private String image_url;


    public BarInfo() {
        this.id = "default";
        this.description = "Lorem Ipsum";
        this.barName = "Barname";
        this.reviews = new ArrayList<>();
        this.image_url = "";
    }

    // Constructor
    public BarInfo(String id, String description, String barName, String image_url) {
        this.id = id;
        this.description = description;
        this.barName = barName;
        this.reviews = reviews;
        this.image_url = image_url;
    }


    /// Getters and setters
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


    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
