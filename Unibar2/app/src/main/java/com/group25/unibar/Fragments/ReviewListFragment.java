package com.group25.unibar.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.group25.unibar.R;
import com.group25.unibar.adapter.ReviewAdapter;
import com.group25.unibar.models.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewListFragment extends Fragment {

    List<Review> reviewList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reviewList = new ArrayList<>();
        reviewList.add(new Review(3.0f,"Hold KÆFT JEG STIV MAND!!! Kom lige alle sammen og se det her sted mand, kæft det fed sted mand mand. Hold KÆFT JEG STIV MAND!!! Kom lige alle sammen og se det her sted mand, kæft det fed sted mand mand. Hold KÆFT JEG STIV MAND!!! Kom lige alle sammen og se det her sted mand, kæft det fed sted mand mand.","TÅGEKAMMERET", "Festaben Marius"));
        reviewList.add(new Review(1.0f,"Ingen damer. Lortebar","Die Rote Zone", "PussySlayer Tobias DD"));
        reviewList.add(new Review(5.0f,"Mødte engang en chick her der jagtede mig med bare patter igennem hele Trøjborg, efter hun havde rystet sin puha for mig ","Nanorama", "Khristoffer Pikfjæs Jackobsen"));
        reviewList.add(new Review(4.0f,"Kæmpe fed bar mand, det her jeg går!","Katrines Kælder", "Simon Sjakbajs #1"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_review_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_reviews);
        ReviewAdapter reviewAdapter = new ReviewAdapter(getContext(), reviewList);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(reviewAdapter);
        return view;
    }


}
