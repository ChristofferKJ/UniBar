package com.group25.unibar.models;

import android.location.Location;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

// https://stackoverflow.com/questions/1944656/android-global-variable problems with sharing viewmodel between service and fragment
public class DeviceLocation {

    private static DeviceLocation mInstance= null;

    public final MutableLiveData<Location> deviceLocation = new MutableLiveData<>();


    public void setDeviceLocation(Location location) {
        deviceLocation.setValue(location);
    }

    public LiveData<Location> getDeviceLocation() {
        return deviceLocation;
    }

    protected DeviceLocation(){}

    public static synchronized DeviceLocation getInstance() {
        if(null == mInstance){
            mInstance = new DeviceLocation();
        }
        return mInstance;
    }

}
