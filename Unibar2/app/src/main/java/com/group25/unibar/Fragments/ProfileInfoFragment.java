package com.group25.unibar.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.content.Context;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

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
import com.group25.unibar.activities.LoginActivity;
import com.group25.unibar.models.User;
import com.group25.unibar.models.UserLocalStore;


///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link ProfileInfoFragment./OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link ProfileInfoFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class ProfileInfoFragment extends Fragment {


    ImageView profilePic;
    TextView textViewName;
    Button buttonLogout;
    // TODO: Rename and change types of parameters
    private String mParam1;
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


//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
