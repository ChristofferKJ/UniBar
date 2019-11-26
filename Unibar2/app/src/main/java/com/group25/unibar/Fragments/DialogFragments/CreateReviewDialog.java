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

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import com.group25.unibar.R;
import com.group25.unibar.models.BarInfo;
import com.group25.unibar.models.Review;
import com.group25.unibar.viewmodels.BarItemViewModel;


// https://developer.android.com/reference/android/app/DialogFragment
public class CreateReviewDialog extends DialogFragment implements View.OnClickListener {

    Review review = new Review();
    Button reviewButton;
    RatingBar ratingBarStars;
    TextView barName;
    EditText barReviewText;

    private BarItemViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this.getActivity()).get(BarItemViewModel.class);
        viewModel.getSelected().observe(this, item -> {
            displayDetails(item);
        });
    }

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
                // Checks if the user has given the bar a rating, otherwise dismisses the dialog.
                if(review.getRating() != 0.0) {
                    Log.d("CreateReviewDialog", "Creating a review!");

                    review.setDescription(barReviewText.getText().toString());
                    review.setBar(barName.getText().toString());
                    // TODO: Get the current user and insert it here
                    review.setUsername("Festaben");

                    Log.d("CreateReviewDialog", "Review for bar:" + review.getBar());
                    Log.d("CreateReviewDialog", "Review Description:" + review.getDescription());
                    Log.d("CreateReviewDialog", "Review Rating:" + review.getRating());
                    Log.d("CreateReviewDialog", "Review made by:" + review.getUsername());
                }else {
                    Log.d("CreateReviewDialog", "No rating set. Dismissing the dialog without making a review.");
                }

                dismiss();
                break;

        }
    }

    public void displayDetails(BarInfo bar){
        barName.setText(bar.getBarName());

    }
}
