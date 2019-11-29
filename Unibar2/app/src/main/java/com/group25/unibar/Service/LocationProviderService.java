package com.group25.unibar.Service;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;

import android.location.Location;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.group25.unibar.Db.BarsDb;
import com.group25.unibar.R;
import com.group25.unibar.models.BarInfo;
import com.group25.unibar.models.DeviceLocation;
import com.group25.unibar.viewmodels.MapViewModel;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


// Made with inspiration from job service in assignment 2
public class LocationProviderService extends Service {

    public MapViewModel viewModel;
    private IBinder binder = new locationBinder();
    private static final int NOTIFY_ID = 37;

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

        Runnable notifications = new Runnable() {
            @Override
            public void run() {
                Log.d("Debug", "Updating notification with bars close");
                startRandomBarDistanceNotifications();
            }
        };
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(notifications, 0 , 2, TimeUnit.MINUTES);

    }

    public void startRandomBarDistanceNotifications() {

        Double distanceToBar;
        int randomNumber = new Random().nextInt(BarsDb.getInstance().get_barList().size());
        BarInfo bar = BarsDb.getInstance().get_barList().get(randomNumber);

        Location deviceLocation = DeviceLocation.getInstance().getDeviceLocation().getValue();

        if (deviceLocation == null){
            distanceToBar = distance(bar.getLatitude(),bar.getLongitude(),56.1715837, 10.190083);
        }
        else{
            Double deviceLat = deviceLocation.getLatitude();
            Double deviceLon = deviceLocation.getLongitude();
            distanceToBar = distance(bar.getLatitude(),bar.getLongitude(),deviceLat, deviceLon);
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) { //needed because channels are not supported on older versions
            NotificationChannel mChannel = new NotificationChannel("myChannel", "Visible myChannel", NotificationManager.IMPORTANCE_LOW);
            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.createNotificationChannel(mChannel);
        }

        Notification notification =
                new NotificationCompat.Builder(this, "myChannel")
                        .setContentTitle(bar.getBarName())
                        .setContentText(round(distanceToBar*1000,2)+" m " + getString(R.string.NotificationContext))
                        .setSmallIcon(R.drawable.ic_sign_in_primary_color)
                        .setTicker("Ticker")
                        .setChannelId("myChannel")
                        .build();

        startForeground(NOTIFY_ID, notification);
    }

    // https://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    private void startLocationUpdates(LocationRequest request, LocationCallback callback) {
        fusedLocationClient.requestLocationUpdates(request, callback, Looper.getMainLooper());
    }


    // https://stackoverflow.com/questions/6981916/how-to-calculate-distance-between-two-locations-using-their-longitude-and-latitu
    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
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
