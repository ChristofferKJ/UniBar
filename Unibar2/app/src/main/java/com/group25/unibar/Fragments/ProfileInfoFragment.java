package com.group25.unibar.Fragments;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.group25.unibar.R;
import com.group25.unibar.Service.LocationProviderService;
import com.group25.unibar.models.DeviceLocation;
import com.group25.unibar.viewmodels.MapViewModel;
import com.group25.unibar.activities.LoginActivity;
import com.group25.unibar.models.User;
import com.group25.unibar.models.UserLocalStore;


public class ProfileInfoFragment extends Fragment {

    ImageView profilePic;
    TextView textViewName;
    Button buttonLogout;

    public ProfileInfoFragment() {
        // Required empty public constructor
    }

    public static ProfileInfoFragment newInstance() {
        ProfileInfoFragment fragment = new ProfileInfoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        UserLocalStore localStore = new UserLocalStore(getContext());
        User loggedInUser = (User) localStore.getLoggedInUser();

        View view = inflater.inflate(R.layout.fragment_profile_info, container, false);
        profilePic = view.findViewById(R.id.ProfileImage_profileInfo);
        textViewName = view.findViewById(R.id.ProfileName_profileInfo);
        buttonLogout = view.findViewById(R.id.buttonLogout);

        textViewName.setText(loggedInUser.getFirst_name() + " " + loggedInUser.getLast_name());
        if (localStore.getUserLoggedIn()) {
            Glide.with(getContext()).load(loggedInUser.getImage_url()).apply(RequestOptions.circleCropTransform()).fallback(R.drawable.app_logo).into(profilePic);
        }else {
            profilePic.setImageResource(R.drawable.app_logo);
            textViewName.setText("");
        }
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                localStore.clearUserData();
                localStore.setUserLoggedIn(false);
                Intent myIntent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(myIntent, 1);
                getActivity().finish();
            }
        });
        return view;
    }


}
