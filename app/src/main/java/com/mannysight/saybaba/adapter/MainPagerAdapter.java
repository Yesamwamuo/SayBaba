package com.mannysight.saybaba.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mannysight.saybaba.fragments.ChatFragment;
import com.mannysight.saybaba.fragments.EmptyFragment;
import com.mannysight.saybaba.fragments.StoryFragment;

public class MainPagerAdapter extends FragmentPagerAdapter {
    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ChatFragment.newInstance();
            case 1:
                return EmptyFragment.newInstance();
            case 2:
                return StoryFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
