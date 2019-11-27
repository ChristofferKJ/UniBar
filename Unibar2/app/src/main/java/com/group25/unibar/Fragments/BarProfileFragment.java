package com.group25.unibar.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.group25.unibar.Fragments.DialogFragments.CreateReviewDialog;
import com.group25.unibar.R;
import com.group25.unibar.models.BarInfo;
import com.group25.unibar.viewmodels.BarItemViewModel;
import com.group25.unibar.viewmodels.MapViewModel;

public class BarProfileFragment extends Fragment implements View.OnClickListener {


    TextView description, barName;
    ImageView barImage;
    Button reviewButton;
    Button checkInButton;
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

        View v =  inflater.inflate(R.layout.fragment_bar_profile, container, false);

        description = v.findViewById(R.id.textView_Description);
        reviewButton = v.findViewById(R.id.button_Review);
        checkInButton = v.findViewById(R.id.button_CheckIn);
        barName = v.findViewById(R.id.textView_BarName);
        barImage = v.findViewById(R.id.imageView_barImage);

        reviewButton.setOnClickListener(this);
        checkInButton.setOnClickListener(this);

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

    public void displayDetails(BarInfo bar){
        barName.setText(bar.getBarName());
        description.setText(bar.getDescription());

    }
}
