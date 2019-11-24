package com.group25.unibar.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.group25.unibar.Fragments.DialogFragments.CreateReviewDialog;
import com.group25.unibar.R;

public class BarProfileFragment extends Fragment implements View.OnClickListener {


    TextView description;
    Button reviewButton;
    Button checkInButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_bar_profile, container, false);

        description = v.findViewById(R.id.textView_Description);
        reviewButton = v.findViewById(R.id.button_Review);
        checkInButton = v.findViewById(R.id.button_CheckIn);

        reviewButton.setOnClickListener(this);
        checkInButton.setOnClickListener(this);
        Log.d("BarProfileFragment", "TEST");

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_Review:
                Log.d("BarProfileFragment", "Review Pressed");
                CreateReviewDialog dialog = new CreateReviewDialog();
                dialog.show(getFragmentManager(), "CreateReviewDialog");

                break;

            case R.id.button_CheckIn:
                Log.d("BarProfileFragment", "Check In Pressed");

                // TODO: Create check in functionality

                break;

        }
    }
}
