package com.group25.unibar.viewmodels;

import android.content.ClipData;
import android.location.Location;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class MapViewModel extends ViewModel {

    public final MutableLiveData<Location> deviceLocation = new MutableLiveData<>();


    public void setDeviceLocation(Location location) {
        deviceLocation.setValue(location);
    }

    public LiveData<Location> getDeviceLocation() {
        return deviceLocation;
    }

}
