package com.group25.unibar.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.group25.unibar.Fragments.BarInfoListFragment;
import com.group25.unibar.Fragments.ProfileInfoFragment;

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
            case 0:
                BarInfoListFragment barInfoFragment = new BarInfoListFragment();
                return barInfoFragment;

            case 1:
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
