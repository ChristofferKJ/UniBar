package com.group25.unibar.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;
import com.group25.unibar.R;
import com.group25.unibar.adapter.TabAdapter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link TabFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabFragment extends Fragment {


    // TODO: Rename and change types of parameters
    private String mParam1;


//    private OnFragmentInteractionListener mListener;

    public TabFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment TabFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TabFragment newInstance(String param1, String param2) {
        TabFragment fragment = new TabFragment();
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
       View view = inflater.inflate(R.layout.fragment_tab, container, false);
       TabLayout tabLayout = (TabLayout)view.findViewById(R.id.tab_layout);
       tabLayout.addTab(tabLayout.newTab().setText((R.string.MapTab)));
       tabLayout.addTab(tabLayout.newTab().setText(R.string.ListTab));

       tabLayout.getTabAt(0).setIcon(R.drawable.unibar_maps_marker);
        tabLayout.getTabAt(1).setIcon(R.drawable.unibar_maps_marker);


       final ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
       final TabAdapter adapter = new TabAdapter
               (getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
       viewPager.setAdapter(adapter);
       viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
       tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
           @Override
           public void onTabSelected(TabLayout.Tab tab) {
               viewPager.setCurrentItem(tab.getPosition());
           }
           @Override
           public void onTabUnselected(TabLayout.Tab tab) {
           }
           @Override
           public void onTabReselected(TabLayout.Tab tab) {
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
