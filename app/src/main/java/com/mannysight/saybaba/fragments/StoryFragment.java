package com.mannysight.saybaba.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.mannysight.saybaba.R;

public class StoryFragment extends BaseFragment {

    public static StoryFragment newInstance() {
        return new StoryFragment();
    }
    @Override
    public int getLayoutResId() {
        return R.layout.fragment_story;
    }

    @Override
    public void inOnCreateView(View root, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    }
}
