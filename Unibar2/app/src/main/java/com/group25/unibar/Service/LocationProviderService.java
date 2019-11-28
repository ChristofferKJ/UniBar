package com.group25.unibar.Service;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStore;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.group25.unibar.activities.MainActivity;
import com.group25.unibar.models.DeviceLocation;
import com.group25.unibar.viewmodels.MapViewModel;



// Made with inspiration from job service in assignment 2
public class LocationProviderService extends Service {

    public MapViewModel viewModel;
    private IBinder binder = new locationBinder();

    // http://www.androiddocs.com/training/location/receive-location-updates.html
    protected LocationRequest createLocationRequest() {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(20000);
        mLocationRequest.setFastestInterval(15000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return mLocationRequest;
    }

    private FusedLocationProviderClient fusedLocationClient;

    public class locationBinder extends Binder{
        public LocationProviderService getService(){
            return LocationProviderService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        LocationRequest request = createLocationRequest();
        LocationCallback locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    Log.d("Debug", "onLocationResult: location equals null");
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    // Update location data
                    if (location != null){
                        Log.d("Debug", "onLocationResult: setting location " + location);
                        DeviceLocation.getInstance().setDeviceLocation(location);
                    }
                }
            };
        };

        startLocationUpdates(request, locationCallback);

    }

    private void startLocationUpdates(LocationRequest request, LocationCallback callback) {
        fusedLocationClient.requestLocationUpdates(request, callback, Looper.getMainLooper());
    }


    public void getLastLocation(){
        // Periodic location updates https://developer.android.com/training/location/retrieve-current.html

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            fusedLocationClient.getLastLocation().addOnFailureListener(getMainExecutor(), new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("Debug", "onFailure: failed to get location" + e.getMessage());
                }
            })
                .addOnSuccessListener(getMainExecutor(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                            Log.d("Debug", "onSuccess: location" + location);
                            viewModel.setDeviceLocation(location);
                        }
                        else {
                            Log.d("Debug", "onSuccess: location is equal to null");
                        }
                    }
                });
        }
    }


}
