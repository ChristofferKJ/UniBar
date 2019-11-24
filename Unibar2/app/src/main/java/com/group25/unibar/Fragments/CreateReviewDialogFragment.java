package com.group25.unibar.Fragments;

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


// https://developer.android.com/reference/android/app/DialogFragment
public class CreateReviewDialogFragment extends DialogFragment implements View.OnClickListener {

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

        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonReview:
                Log.d("CreateReviewDialogFrag", "Review Pressed");

                break;

        }
    }
}
