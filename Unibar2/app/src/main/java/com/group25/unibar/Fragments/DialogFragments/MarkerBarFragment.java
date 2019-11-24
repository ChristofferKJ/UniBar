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

public class MarkerBarFragment extends DialogFragment{

    public MarkerBarFragment() { }

    public static MarkerBarFragment newInstance(String barName) {
        MarkerBarFragment frag = new MarkerBarFragment();
        Bundle args = new Bundle();
        args.putString("barName", barName);
        frag.setArguments(args);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.map_marker_bar_dialogfragment, container);
        TextView textviewTitle = v.findViewById(R.id.barviewTitle);
        textviewTitle.setText(getArguments().getString("barName"));
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}