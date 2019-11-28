package com.group25.unibar.Fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.group25.unibar.Db.BarsDb;
import com.group25.unibar.R;
import com.group25.unibar.adapter.BarInfoAdapter;
import com.group25.unibar.models.BarInfo;
import com.group25.unibar.viewmodels.BarItemViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BarInfoListFragment extends Fragment {

    List<BarInfo> barInfoList;
    List<BarInfo> randomBarsList;
    private BarItemViewModel viewModel;
    public BarInfoAdapter barInfoAdapter;
    private GridLayoutManager layoutManager;


    public BarInfoListFragment() {
        // Required empty public constructor
    }

    public static BarInfoListFragment newInstance() {
        BarInfoListFragment fragment = new BarInfoListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("BarInfoListFragment", "onCreate");
        barInfoList = new ArrayList<>();
        barInfoList = BarsDb.getInstance().get_barList();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bar_info_list, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        if(getParentFragment().toString().contains("ProfileInfoFragment")){
            Log.d("BarInfoListFragment", "Finding 4 random bars for you!");
            Random r = new Random();
            int min = 0;
            int max = 29;
            int randomNumber = r.nextInt(max-min) + min;
            randomBarsList = barInfoList.subList(randomNumber, randomNumber + 4);
            barInfoAdapter = new BarInfoAdapter(getContext(), randomBarsList);
        }else {
            barInfoAdapter = new BarInfoAdapter(getContext(), barInfoList);
        }

        if((getContext().getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
            >= Configuration.SCREENLAYOUT_SIZE_LARGE){
            // If device is tablet, make 4 columns instead of 2. Inspiration found here: https://stackoverflow.com/questions/40157799/correctly-detect-android-device-type/40219432

            layoutManager = new GridLayoutManager(getContext(), 4);
        } else if(getParentFragment().toString().contains("ProfileInfoFragment")){
            // If the fragment is shown in profileinfofragment, only show 1 column
            layoutManager = new GridLayoutManager(getContext(), 1);
        }
        else {
            // Default column count: 2
            layoutManager = new GridLayoutManager(getContext(), 2);
        }
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(barInfoAdapter);
        return view;
    }
}
