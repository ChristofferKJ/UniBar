package com.group25.unibar.models;

public class Review {

    private Float rating;
    private String description;
    private String barName;
    private String username;

    // Constructor
    public Review() {
        this.rating = 0.0f;
        this.description = "";
        this.barName = "";
        this.username = "";
    }

    public Review(Float rating, String description, String barName, String username) {
        this.rating = rating;
        this.description = description;
        this.barName = barName;
        this.username = username;
    }

    // Getters and setters
    public String getBarName() {
        return barName;
    }

    public void setBarName(String barName) {
        this.barName = barName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
