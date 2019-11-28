package com.group25.unibar.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
import com.group25.unibar.Db.BarsDb;
import com.group25.unibar.Helpers.CSVHelper;
import com.group25.unibar.R;
import com.group25.unibar.adapter.BarmapAdapter;
import com.group25.unibar.models.BarInfo;
import com.group25.unibar.models.DeviceLocation;
import com.group25.unibar.models.User;
import com.group25.unibar.models.UserLocalStore;
import com.group25.unibar.viewmodels.BarItemViewModel;
import com.group25.unibar.viewmodels.MapViewModel;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import io.github.yavski.fabspeeddial.FabSpeedDial;

import static android.content.ContentValues.TAG;
import static com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelState.COLLAPSED;
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

    private ArrayList<BarInfo> bars;
    private TextView slideup_barname;
    private FabSpeedDial fab;
    private SlidingUpPanelLayout slideup_panel;
    private Boolean night_mode_on;
    private EditText editText_searchBar;
    private ListView listView_bars;
    private BarmapAdapter barmapAdapter;
    private BarItemViewModel barItemViewModel;
    private UserLocalStore usl;

    private Marker userPosition;
    private Location myLocation;

    public BarMapFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate: her kalder vi");


        barItemViewModel = ViewModelProviders.of((FragmentActivity) getContext()).get(BarItemViewModel.class);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_bar_map, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        bars = BarsDb.getInstance().get_barList();

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        slideup_barname = getView().findViewById(R.id.slideup_barname);
        slideup_panel = getView().findViewById(R.id.barmap_sliding_up_panel);
        editText_searchBar = getView().findViewById(R.id.barmap_searchbar);
        listView_bars = getView().findViewById(R.id.listview_barnames);
        barmapAdapter = new BarmapAdapter(getContext(), bars);
        listView_bars.setAdapter(barmapAdapter);

        fab = (FabSpeedDial) getView().findViewById(R.id.barmap_fab);


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

        bars.forEach((x) -> {
            mMap.addMarker(new MarkerOptions().position(new LatLng(x.getLatitude(), x.getLongitude()))
                    .title(x.getBarName()).icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("unibar_maps_marker_png", 125, 125))));
        });

        usl = new UserLocalStore(getContext());
        // Setting up current user location
        MarkerOptions a = new MarkerOptions().zIndex(100f).position(new LatLng(0, 0)).icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("user_maps_marker", 100,100))).title(usl.getLoggedInUser().getFirst_name());
        Marker m = mMap.addMarker(a);

        DeviceLocation.getInstance().getDeviceLocation().observe(this, location -> {

            myLocation = location;
            m.setPosition(new LatLng(location.getLatitude(), location.getLongitude()));

        });


        fab.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true;
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.mapType_call:
                        if (night_mode_on) {
                            mMap.setMapStyle(
                                    MapStyleOptions.loadRawResourceStyle(
                                            getContext(), R.raw.retro_map_style));
                            night_mode_on = false;
                            return true;
                        } else {
                            mMap.setMapStyle(
                                    MapStyleOptions.loadRawResourceStyle(
                                            getContext(), R.raw.night_mode_maps_style));
                            night_mode_on = true;
                            return true;
                        }

                    case R.id.searchBar_call:

                        slideup_panel.setPanelState(EXPANDED);
                        editText_searchBar.requestFocus();
                        return true;


                    case R.id.findUser_call:

                        if(myLocation == null)
                            return true; 

                        LatLng userLocation = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
                        CameraPosition cam = CameraPosition.builder()
                                .target(userLocation)
                                .zoom(18)
                                .bearing(0)
                                .build();
                        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cam), 500, null);

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


        listView_bars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                BarInfo tempBar = bars.get(position);

                slideup_barname.setText(tempBar.getBarName());

                LatLng tempbarLatLng = new LatLng(tempBar.getLatitude(), tempBar.getLongitude());

                CameraPosition cam = CameraPosition.builder()
                        .target(tempbarLatLng)
                        .zoom(18)
                        .bearing(0)
                        .build();

                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cam), 500, null);

            }
        });


        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {


                if(marker.getTitle().equals(usl.getLoggedInUser().getFirst_name()))
                    return;

                BarInfo tempBar = new BarInfo();
                String selectedBarName = slideup_barname.getText().toString();

                for (BarInfo bar : BarsDb.getInstance().get_barList()) {

                    if (bar.getBarName().equals(selectedBarName)) {
                        tempBar = bar;
                        break;
                    }
                }

                barItemViewModel.select(tempBar);
                Navigation.findNavController(getView()).navigate(R.id.action_tabFragment_to_barProfileFragment);
            }
        });

        editText_searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                CSVHelper csvHelper = new CSVHelper(getContext());
                try {
                    bars.clear();
                    bars.addAll(csvHelper.CsvToBars("Barlist_med_koordinater.csv"));


                } catch (IOException e) {
                    e.printStackTrace();
                }

                ArrayList<BarInfo> tempList = new ArrayList<BarInfo>();

                for (BarInfo bar : bars) {
                    String s1 = bar.getBarName().toLowerCase();
                    String s2 = s.toString().toLowerCase();

                    if (s1.contains(s2))
                        tempList.add(bar);

                }

                barmapAdapter.clear();
                barmapAdapter.addAll(tempList);
                barmapAdapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        slideup_panel.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {

                if (slideOffset < 0) {
                    return;
                }

                float paddingOffset = (slideOffset * 710 + 210);
                mMap.setPadding(0, 0, 0, (int) paddingOffset);

                return;
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {


                switch (previousState) {
                    case DRAGGING: {
                        if (newState == EXPANDED) {
                            mMap.setPadding(0, 0, 0, 920);
                        }

                        if (newState == COLLAPSED) {
                            mMap.setPadding(0, 0, 0, 210);
                        }

                        if (newState == HIDDEN) {
                            mMap.setPadding(0, 0, 0, 0);
                        }
                    }
                }
            }
        });


        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                editText_searchBar.clearFocus();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);

                imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);

                slideup_panel.setPanelState(HIDDEN);

                mMap.setPadding(0, 0, 0, 0);
            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                if (slideup_panel.getPanelState() == HIDDEN) {
                    slideup_panel.setPanelState(COLLAPSED);
                }

                if (slideup_panel.getPanelState() == EXPANDED) {
                    slideup_barname.setText(marker.getTitle());
                    return false;
                }

                slideup_panel.setPanelHeight(200);
                slideup_barname.setText(marker.getTitle());
                mMap.setPadding(0, 0, 0, 210);
                return false;
            }
        });
    }
}