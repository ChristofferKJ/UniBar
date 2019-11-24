package com.group25.unibar.Fragments;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.group25.unibar.Fragments.DialogFragments.MarkerBarDialog;
import com.group25.unibar.Helpers.CSVHelper;
import com.group25.unibar.R;
import com.group25.unibar.models.Bar;

import java.io.IOException;
import java.util.ArrayList;


//                                                                          //
//                                                                          //
//                                                                          //
// INSPIRATION FRA https://demonuts.com/android-google-map-in-fragment/     //
//                                                                          //
//                                                                          //
//                                                                          //

public class BarMapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private ArrayList<Bar> bars;

    public BarMapFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_bar_map, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        return rootView;
    }


    // https://stackoverflow.com/questions/14851641/change-marker-size-in-google-maps-api-v2
    public Bitmap resizeMapIcons(String iconName, int width, int height) {
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier(iconName, "drawable", getContext().getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onMapReady(GoogleMap mMap) {

        mMap.setOnMarkerClickListener(this);

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        mMap.clear(); //clear old markers

        LatLng Aarhus = new LatLng(56.132939, 10.203921);

        CameraPosition googlePlex = CameraPosition.builder()
                .target(Aarhus)
                .zoom(12)
                .bearing(0)
                .build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 3000, null);


        CSVHelper csvHelper = new CSVHelper(getContext());
        try {
            bars = csvHelper.CsvToBars("Barlist_med_koordinater.csv");

        } catch (IOException e) {
            e.printStackTrace();
        }


        bars.forEach((x) -> {


            mMap.addMarker(new MarkerOptions().position(new LatLng(x.Latitude, x.Longitude))
                    .title(x.Name).icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("unibar_maps_marker_png", 125, 125))));

        });
    }


    @Override
    public boolean onMarkerClick(Marker marker) {

        FragmentManager fm = getFragmentManager();

        MarkerBarDialog markerBarDialog = MarkerBarDialog.newInstance(marker.getTitle());
        markerBarDialog.show(fm, "cool");
        return false;

    }
}