package com.group25.unibar.activities;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.group25.unibar.R;

public class BarMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;

        LatLng TheoBar = new LatLng(56.17212200, 10.20329030);
        LatLng KOMMAbar = new LatLng(56.17302510,10.20448570);
        LatLng FRED = new LatLng(56.17282200,10.20622490);
        LatLng Fredagsbar = new LatLng(56.17118420,10.19021480);

        LatLng Aarhus = new LatLng(56.162939,10.203921);

        mMap.addMarker(new MarkerOptions().position(TheoBar).title("Theobar"));
        mMap.addMarker(new MarkerOptions().position(KOMMAbar).title("Kommabar"));
        mMap.addMarker(new MarkerOptions().position(FRED).title("FRED"));
        mMap.addMarker(new MarkerOptions().position(Fredagsbar).title("Fredagsbar.dk"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Aarhus, 12.0f));
    }
}