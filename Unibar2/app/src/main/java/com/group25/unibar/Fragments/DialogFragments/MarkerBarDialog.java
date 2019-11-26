package com.group25.unibar.Fragments.DialogFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.group25.unibar.R;


public class MarkerBarDialog extends DialogFragment{

    public MarkerBarDialog() { }

    public static MarkerBarDialog newInstance(String barName) {
        MarkerBarDialog frag = new MarkerBarDialog();
        Bundle args = new Bundle();
        args.putString("barName", barName);
        frag.setArguments(args);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_map_marker_bar, container);
        TextView textviewTitle = v.findViewById(R.id.barviewTitle);
        textviewTitle.setText(getArguments().getString("barName"));
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}