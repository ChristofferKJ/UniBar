package com.group25.unibar.viewmodels;

import android.content.ClipData;
import android.location.Location;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class MapViewModel extends ViewModel {

    private final MutableLiveData<Location> deviceLocation = new MutableLiveData<>();


    public void setDeviceLocation(Location location) {
        Log.d("Debug", "setDeviceLocation: updating device location with " + location.getLatitude() + " " + location.getLongitude() );
        deviceLocation.setValue(location);
    }

    public LiveData<Location> getSelected() {
        Log.d("Debug", "getSelected: returning location with " + deviceLocation);
        return deviceLocation;
    }

}
