package com.mannysight.saybaba.view;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.mannysight.saybaba.R;

public class AnimTabsView extends FrameLayout implements ViewPager.OnPageChangeListener {

    private ImageView mCameraView;
    private ImageView mStartView;
    private ImageView mEndView;
    private ImageView mBottomView;

    private ArgbEvaluator argbEvaluator;
    private View mIndicator;
    private int centerColor;
    private int endsColor;

    public AnimTabsView(@NonNull Context context) {
        this(context, null);
    }

    public AnimTabsView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnimTabsView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_anim_tabs, this, true);
        mCameraView = findViewById(R.id.center_image);
        mStartView = findViewById(R.id.left_chat);
        mEndView = findViewById(R.id.right_story);
        mBottomView = findViewById(R.id.bottom_saved);
        mIndicator = findViewById(R.id.indicator);

        centerColor = ContextCompat.getColor(getContext(), R.color.white);
        endsColor = ContextCompat.getColor(getContext(), R.color.dark_grey);
        argbEvaluator = new ArgbEvaluator();
    }

    public void setUpWithViewPager(ViewPager viewPager) {
        viewPager.addOnPageChangeListener(this);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (position == 0) {
            setColor(1 - positionOffset);
        } else if (position == 1) {
            setColor(positionOffset);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void setColor(float fractionFromCenter) {
        int color = (int) argbEvaluator.evaluate(fractionFromCenter, centerColor, endsColor);
        mCameraView.setColorFilter(color);
        mStartView.setColorFilter(color);
        mEndView.setColorFilter(color);
    }
}
