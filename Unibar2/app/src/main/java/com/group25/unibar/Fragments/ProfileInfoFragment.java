package com.group25.unibar.Fragments;

import android.content.ComponentName;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.group25.unibar.R;
import com.group25.unibar.Service.LocationProviderService;
import com.group25.unibar.models.DeviceLocation;
import com.group25.unibar.viewmodels.MapViewModel;


///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link ProfileInfoFragment./OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link ProfileInfoFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class ProfileInfoFragment extends Fragment {


    // TODO: Rename and change types of parameters
    private String mParam1;
    ImageView profilePic;
    TextView name, faculty;

//    private OnFragmentInteractionListener mListener;

    public ProfileInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ProfileInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
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

        View view = inflater.inflate(R.layout.fragment_profile_info, container, false);
        profilePic = view.findViewById(R.id.ProfileImage_profileInfo);
        name = view.findViewById(R.id.ProfileName_profileInfo);
        faculty = view.findViewById(R.id.ProfileFaculty_profileInfo);
        profilePic.setImageResource(R.drawable.billede);

        return view;
    }


}
