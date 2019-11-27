package com.group25.unibar.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.slidingpanelayout.widget.SlidingPaneLayout;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.internal.NavigationMenu;
import com.group25.unibar.Fragments.DialogFragments.MarkerBarDialog;
import com.group25.unibar.Helpers.CSVHelper;
import com.group25.unibar.R;
import com.group25.unibar.models.Bar;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.io.IOException;
import java.util.ArrayList;

import io.github.yavski.fabspeeddial.FabSpeedDial;

import static android.content.ContentValues.TAG;
import static com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelState.COLLAPSED;
import static com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelState.DRAGGING;
import static com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelState.EXPANDED;
import static com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelState.HIDDEN;


//                                                                          //
//                                                                          //
//                                                                          //
// INSPIRATION FRA https://demonuts.com/android-google-map-in-fragment/     //
//                                                                          //
//                                                                          //
//                                                                          //

public class BarMapFragment extends Fragment implements OnMapReadyCallback {

    private ArrayList<Bar> bars;
    private TextView slideup_barname;
    private FabSpeedDial fab;
    private SlidingUpPanelLayout slideup_panel;
    private Boolean night_mode_on;

    public BarMapFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_bar_map, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        slideup_barname = getView().findViewById(R.id.slideup_barname);
        slideup_panel = getView().findViewById(R.id.barmap_sliding_up_panel);

        fab = (FabSpeedDial)getView().findViewById(R.id.barmap_fab);
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

        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.night_mode_maps_style));
        night_mode_on = true;


        mMap.clear(); //clear old markers
        LatLng Aarhus = new LatLng(56.132939, 10.203921);
        CameraPosition googlePlex = CameraPosition.builder()
                .target(Aarhus)
                .zoom(12)
                .bearing(0)
                .build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 500, null);

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


        fab.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true;
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {

                switch (menuItem.getItemId())
                {
                    case R.id.mapType_call:
                        if(night_mode_on)
                        {
                            mMap.setMapStyle(
                                    MapStyleOptions.loadRawResourceStyle(
                                            getContext(), R.raw.retro_map_style));
                            night_mode_on = false;
                            return true;
                        }
                        else
                        {
                            mMap.setMapStyle(
                                    MapStyleOptions.loadRawResourceStyle(
                                            getContext(), R.raw.night_mode_maps_style));
                            night_mode_on = true;
                            return true;
                        }

                    case R.id.searchBar_call:
                        return true;

                    case R.id.toUser_call:
                        return true;
                }

                mMap.setOnInfoWindowCloseListener(new GoogleMap.OnInfoWindowCloseListener() {
                    @Override
                    public void onInfoWindowClose(Marker marker) {
                    }
                });
                return false;
            }

            @Override
            public void onMenuClosed() {

            }


        });

        slideup_panel.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset)
            {

                if(slideOffset < 0)
                {return;}

                float paddingOffset = (slideOffset*320+210);
                mMap.setPadding(0,0,0, (int)paddingOffset);

                return;
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {


                switch (previousState) {
                    case DRAGGING:
                    {
                        if(newState == EXPANDED)
                        {
                            mMap.setPadding(0,0,0, 530);
                        }

                        if(newState == COLLAPSED)
                        {
                            mMap.setPadding(0,0,0, 210);
                        }
                    }
                }
            }
        });


        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                slideup_panel.setPanelState(HIDDEN);

                mMap.setPadding(0,0,0,0);
            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                if(slideup_panel.getPanelState() == HIDDEN) {
                    slideup_panel.setPanelState(COLLAPSED);
                }

                if(slideup_panel.getPanelState() == EXPANDED) {
                    slideup_barname.setText(marker.getTitle());
                    return false;
                }

                slideup_panel.setPanelHeight(200);
                slideup_barname.setText(marker.getTitle());
                mMap.setPadding(0,0,0,210);
                return false;
            }
        });
    }
}