package com.group25.unibar.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.group25.unibar.R;
import com.group25.unibar.models.BarInfo;

import java.util.List;

// Made with inspiration from previous assignments and https://www.androidhive.info/2016/05/android-working-with-card-view-and-recycler-view/

public class BarInfoAdapter extends RecyclerView.Adapter<BarInfoAdapter.BarInfoViewHolder> {

    private Context mContext;
    private List<BarInfo> barList;

    public class BarInfoViewHolder extends RecyclerView.ViewHolder {
        public TextView barName, description;
        public ImageView barImage;

        public BarInfoViewHolder(View view) {
            super(view);
            barName = (TextView) view.findViewById(R.id.barName_barInfoList);
            description = (TextView) view.findViewById(R.id.barDescription_barInfoList);
            barImage = (ImageView) view.findViewById(R.id.barImage_barInfoList);

        }
    }


    @NonNull
    @Override
    public BarInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_bar_item, parent, false);

        return new BarInfoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BarInfoViewHolder holder, int position) {
        BarInfo bar = barList.get(position);
        holder.barName.setText(bar.getBarName());
        holder.description.setText(bar.getDescription());

        // loading bar image using Glide library
        Glide.with(mContext).load(bar.getThumbnail()).into(holder.barImage);

        holder.barImage.setOnClickListener(view -> Log.d("Debug", "onClick: "));
    }


    @Override
    public int getItemCount() {
        return barList.size();
    }

    public BarInfoAdapter(Context mContext, List<BarInfo> barList) {
        this.mContext = mContext;
        this.barList = barList;
    }


}
