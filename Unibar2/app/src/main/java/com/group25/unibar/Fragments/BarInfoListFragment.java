package com.group25.unibar.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.group25.unibar.R;
import com.group25.unibar.adapter.BarInfoAdapter;
import com.group25.unibar.models.BarInfo;
import com.group25.unibar.viewmodels.BarItemViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Executor;


///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link BarInfoListFragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link BarInfoListFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class BarInfoListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    List<BarInfo> barInfoList;
    private BarItemViewModel viewModel;

//    private OnFragmentInteractionListener mListener;

    public BarInfoListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BarInfoListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BarInfoListFragment newInstance() {
        BarInfoListFragment fragment = new BarInfoListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        barInfoList = new ArrayList<>();
        barInfoList.add(new BarInfo("1","Description","TÅGEKAMMERET", "https://scontent-arn2-1.xx.fbcdn.net/v/t31.0-8/s960x960/1401917_773244319357255_365398689_o.png?_nc_cat=111&_nc_ohc=_zAtfv6ZVsMAQkHNBIsAPcisDisCcPAQFfzjvAnRiKg3E8KVDI6AqrYJA&_nc_ht=scontent-arn2-1.xx&oh=2c2ee0858100d974b0da46abd2f6b706&oe=5E4D3E68", 1.0));
        barInfoList.add(new BarInfo("2","Description","Die Rote Zone", "https://scontent-arn2-1.xx.fbcdn.net/v/t31.0-8/p960x960/1412749_733196930113613_5373507156254861526_o.jpg?_nc_cat=104&_nc_ohc=J-Kpn_TqcJ0AQnX6pNm5RKRMpToREVPyvRZ8QpWGXSVBPC4zeYQrxVDFw&_nc_ht=scontent-arn2-1.xx&oh=c8ba13562b73ccf71f7f9d074fa39a43&oe=5E457C57", 1.0));
        barInfoList.add(new BarInfo("3","Description","Nanorama", "http://inano.au.dk/fileadmin/_processed_/csm_nanorama_db51505ed2.png",1.0));
        barInfoList.add(new BarInfo("4","Description","Katrines Kælder", "https://scontent-arn2-2.xx.fbcdn.net/v/t1.0-9/13166_441233562611984_1450333570_n.png?_nc_cat=105&_nc_ohc=_lg3n-TStKUAQlqdJpCYGfpZMn3-e2VO3Qaahv2mqPSMyhOtWTqMi4NSQ&_nc_ht=scontent-arn2-2.xx&oh=153a3d397ac5e74a0a4589375e4b49e7&oe=5E4049DD", 1.0));



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bar_info_list, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        BarInfoAdapter barInfoAdapter = new BarInfoAdapter(getContext(), barInfoList);


        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(barInfoAdapter);
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
