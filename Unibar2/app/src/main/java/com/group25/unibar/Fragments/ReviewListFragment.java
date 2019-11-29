package com.group25.unibar.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.group25.unibar.R;
import com.group25.unibar.adapter.ReviewAdapter;
import com.group25.unibar.models.BarInfo;
import com.group25.unibar.models.Review;
import com.group25.unibar.viewmodels.BarItemViewModel;
import com.group25.unibar.viewmodels.ReviewListViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ReviewListFragment extends Fragment {

    List<Review> reviewList = new ArrayList<>();

    private BarItemViewModel barItemViewModel;
    private ReviewListViewModel reviewListViewModel;
    ReviewAdapter reviewAdapter = new ReviewAdapter(getContext(), reviewList);


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reviewListViewModel = ViewModelProviders.of(this.getActivity()).get(ReviewListViewModel.class);
        try {
            reviewList.addAll(reviewListViewModel.getAllReviews());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        barItemViewModel = ViewModelProviders.of(this.getActivity()).get(BarItemViewModel.class);
        barItemViewModel.getSelected().observe(this, item -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                sortReviews(item);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_review_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_reviews);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(reviewAdapter);

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sortReviews(BarInfo bar){
        // Removes the reviews from the list, if the reviews are not for the specific bar. Also flips the array so newest reviews are shown first.
        if(reviewList != null) {
            Collections.reverse(reviewList);
            reviewList.removeIf(review -> !review.getBarName().equals(bar.getBarName()));
            reviewAdapter.notifyDataSetChanged();
        }
    }
}
