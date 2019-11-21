package com.group25.unibar.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import com.group25.unibar.R;
import com.group25.unibar.viewmodels.ProfileInfoViewModel;

public class ProfileInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_info);

        // Create a ViewModel the first time the system calls an activity's onCreate() method.
        // Re-created activities receive the same MyViewModel instance created by the first activity.

        ProfileInfoViewModel model = ViewModelProviders.of(this).get(ProfileInfoViewModel.class);
        model.getUser().observe(this, user -> {
            // update UI
        });
    }
}
