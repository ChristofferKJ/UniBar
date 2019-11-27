package com.group25.unibar.activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import com.group25.unibar.R;
import com.group25.unibar.Service.LocationProviderService;

public class MainActivity extends AppCompatActivity {

    //Bound service
    LocationProviderService mService;
    boolean mBound = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_nav_host);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Debug", "onStart MainActivity: binding to service");
        //Bind to our location service
        Intent serviceIntent = new Intent(this, LocationProviderService.class);
        bindService(serviceIntent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);
        mBound = false;
    }

    /**
     * Defines callbacks for service binding, passed to bindService()
     * https://developer.android.com/guide/components/bound-services?fbclid=IwAR0bXS9zyGA-ksh4HQTFBEcxXAsbDjfzN2tJvDfiTbR7cACRrOrMzcwtDvA
     */
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {

            LocationProviderService.locationBinder binder = (LocationProviderService.locationBinder) service;
            mService = binder.getService();
            Log.d("Debug", "onServiceConnected: " + mService);
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };


}




