package com.group25.unibar.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.group25.unibar.R;
import com.group25.unibar.models.Review;

import java.util.List;

// Made with inspiration from previous assignments and https://www.androidhive.info/2016/05/android-working-with-card-view-and-recycler-view/

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private Context mContext;
    private List<Review> reviewList;

    public class ReviewViewHolder extends RecyclerView.ViewHolder {
        public TextView userName, description;
        public RatingBar ratingBar;

        public ReviewViewHolder(View view) {
            super(view);
            userName = view.findViewById(R.id.userName_reviewListItem);
            description = view.findViewById(R.id.reviewDescription_reviewListItem);
            ratingBar = view.findViewById(R.id.ratingBar_reviewListItem);
        }
    }


    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_review_item, parent, false);

        return new ReviewViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Review review = reviewList.get(position);
        Log.d("TEST", review.getUsername().toString());
        Log.d("TEST", review.getDescription().toString());
        holder.userName.setText(review.getUsername());
        holder.description.setText(review.getDescription());
        holder.ratingBar.setRating(review.getRating());

        // loading userimage using Glide library
        //Glide.with(mContext).load(review.getImage_url()).into(holder.userImage);
    }


    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public ReviewAdapter(Context mContext, List<Review> reviewList) {
        this.mContext = mContext;
        this.reviewList = reviewList;
    }


}
