package com.example.lab2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class VKAdapter extends FragmentStatePagerAdapter {

    public VKAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return VKFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return 1000000;
    }

}
