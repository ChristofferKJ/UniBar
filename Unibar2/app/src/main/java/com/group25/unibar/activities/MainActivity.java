package com.group25.unibar.activities;

import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.group25.unibar.ProfileInfoFragment;
import com.group25.unibar.R;

public class MainActivity extends AppCompatActivity implements ProfileInfoFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_nav_host);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
