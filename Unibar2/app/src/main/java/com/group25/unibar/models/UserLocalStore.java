package com.group25.unibar.models;

import android.content.Context;
import android.content.SharedPreferences;

//https://www.youtube.com/watch?v=nHmPErsoe64
public class UserLocalStore {
    public static final String SP_NAME = "userDetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context) {
        this.userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void StoreUserData(User user) {
        SharedPreferences.Editor editor = userLocalDatabase.edit();
        editor.putString("firstName", user.first_name);
        editor.putString("lastName", user.last_name);
        editor.putString("email", user.email);
        editor.putString("imageUrl", user.image_url);
        editor.commit();
    }

    public User getLoggedInUser() {
        String firstName = userLocalDatabase.getString("firstName", "");
        String lastName = userLocalDatabase.getString("lastName", "");
        String email = userLocalDatabase.getString("email", "");
        String imageUrl = userLocalDatabase.getString("imageUrl", "");

        return new User(firstName, lastName, email, imageUrl);
    }

    public void setUserLoggedIn(boolean loggedIn) {
        SharedPreferences.Editor editor = userLocalDatabase.edit();
        editor.putBoolean("loggedIn", loggedIn);
        editor.commit();
    }

    public void clearUserData(){
        SharedPreferences.Editor editor = userLocalDatabase.edit();
        editor.clear();
    }
}
