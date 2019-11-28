package com.group25.unibar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.group25.unibar.models.UserLocalStore;

//https://android.jlelse.eu/login-and-main-activity-flow-a52b930f8351
public class MainEmptyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent myIntent;
        UserLocalStore localStore = new UserLocalStore(this);

        if (localStore.getUserLoggedIn()) {
            //User logged in therefore navigate to main activity
            myIntent = new Intent(this, MainActivity.class);
        } else {
            //User not logged in therefore navigate to login activity
            myIntent = new Intent(this, LoginActivity.class);

        }

        startActivityForResult(myIntent, 1);
        finish();
    }
}
