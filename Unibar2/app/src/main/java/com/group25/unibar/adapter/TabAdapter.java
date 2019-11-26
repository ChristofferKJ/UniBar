package com.group25.unibar.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.group25.unibar.Fragments.BarInfoListFragment;
import com.group25.unibar.Fragments.ProfileInfoFragment;

// Made with inspiration from https://developer.android.com/guide/navigation/navigation-swipe-view

public class TabAdapter extends FragmentStatePagerAdapter {

    int numberOfTabs;

    public TabAdapter(FragmentManager fragmentManager, int numberOfTabs){
        super(fragmentManager);
        this.numberOfTabs = numberOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: // Insert map view fragment TODO
                BarInfoListFragment mapFragment = new BarInfoListFragment();
                return mapFragment;

            case 1:
                BarInfoListFragment barInfoFragment = new BarInfoListFragment();
                return barInfoFragment;

            case 2:
                ProfileInfoFragment profileInfoFragment = new ProfileInfoFragment();
                return profileInfoFragment;


            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
