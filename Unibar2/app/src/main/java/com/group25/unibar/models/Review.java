package com.group25.unibar.models;

public class Review {

    private Double rating;
    private String description;
    private String bar;
    private String username;

    // Constructor
    public Review(Double rating, String description, String bar, String username) {
        this.rating = rating;
        this.description = description;
        this.bar = bar;
        this.username = username;
    }

    // Getters and setters
    public String getBar() {
        return bar;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
