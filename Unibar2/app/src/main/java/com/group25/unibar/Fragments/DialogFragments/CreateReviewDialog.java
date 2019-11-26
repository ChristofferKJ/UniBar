package com.group25.unibar.Fragments.DialogFragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.group25.unibar.R;
import com.group25.unibar.models.Review;


// https://developer.android.com/reference/android/app/DialogFragment
public class CreateReviewDialog extends DialogFragment implements View.OnClickListener {

    Review review = new Review();
    Button reviewButton;
    RatingBar ratingBarStars;
    TextView barName;
    EditText barReviewText;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.dialog_create_review, container, false);

        reviewButton = v.findViewById(R.id.buttonReview);
        ratingBarStars = v.findViewById(R.id.ratingBar);
        barName = v.findViewById(R.id.textViewBarName);
        barReviewText = v.findViewById(R.id.editTextBarReview);

        reviewButton.setOnClickListener(this);


        // https://www.mkyong.com/android/android-rating-bar-example/
        ratingBarStars.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                Log.d("CreateReviewDialog", "Rating is: " + rating);
                review.setRating(rating);

            }
        });


        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonReview:
                Log.d("CreateReviewDialogFrag", "Creating a review!");

                review.setDescription(barReviewText.getText().toString());
                review.setBar(barName.getText().toString());
                // TODO: Get the current user and insert it here
                review.setUsername("Festaben");

                Log.d("CreateReviewDialogFrag", "Review for bar:" + review.getBar());
                Log.d("CreateReviewDialogFrag", "Review Description:" + review.getDescription());
                Log.d("CreateReviewDialogFrag", "Review Rating:" + review.getRating());
                Log.d("CreateReviewDialogFrag", "Review made by:" + review.getUsername());

                dismiss();
                break;

        }
    }
}
