package com.group25.unibar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.group25.unibar.R;
import com.group25.unibar.models.Bar;

import java.util.ArrayList;

public class BarmapAdapter extends ArrayAdapter<Bar> {



    Context context;
    ArrayList<Bar> bars;

    public BarmapAdapter(@NonNull Context context, ArrayList<Bar> bars) {
        super(context, R.layout.listview_item_barmap, R.id.barname_listitem, bars);
        this.context = context;
        this.bars = bars;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.listview_item_barmap, parent, false);
        TextView name = row.findViewById(R.id.barname_listitem);

        name.setText(bars.get(position).Name);
        return row;
    }
}
