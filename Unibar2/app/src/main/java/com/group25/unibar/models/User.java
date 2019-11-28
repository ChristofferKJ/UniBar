package com.group25.unibar.models;

public class User {
    String first_name, last_name, email, image_url;

    public User(String first_name, String last_name, String email, String image_url) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.image_url = image_url;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getImage_url() {
        return image_url;
    }
}
