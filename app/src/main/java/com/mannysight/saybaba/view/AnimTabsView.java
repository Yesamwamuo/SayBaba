package com.mannysight.saybaba.view;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
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
    private int mEndViewsTranslation;
    private int mIndicatorTranslationX;
    private int mCenterTranslationY;

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


    public ImageView getmCameraView() {
        return mCameraView;
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


        mIndicatorTranslationX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                80, getResources().getDisplayMetrics());

        mBottomView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mEndViewsTranslation = (int) (mBottomView.getX() - mStartView.getX() - mIndicatorTranslationX);
                mCenterTranslationY = getHeight() - mBottomView.getBottom();
                mBottomView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });


    }

    public void setUpWithViewPager(final ViewPager viewPager) {

        viewPager.addOnPageChangeListener(this);

        mStartView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewPager.getCurrentItem() != 0) {
                    viewPager.setCurrentItem(0);
                }
            }
        });
        mEndView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewPager.getCurrentItem() != 2) {
                    viewPager.setCurrentItem(2);
                }
            }
        });
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (position == 0) {
            setColor(1 - positionOffset);
            moveViews(1 - positionOffset);
            moveAndScaleCenter(1 - positionOffset);
            mIndicator.setTranslationX((positionOffset - 1) * mIndicatorTranslationX);
        } else if (position == 1) {
            setColor(positionOffset);
            moveViews(positionOffset);
            moveAndScaleCenter(positionOffset);
            mIndicator.setTranslationX(positionOffset * mIndicatorTranslationX);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void moveAndScaleCenter(float fractionFromCenter) {
        float scale = .7f + ((1 - fractionFromCenter) * .3f);

        mCameraView.setScaleX(scale);
        mCameraView.setScaleY(scale);

        int translation = (int) (fractionFromCenter * mCenterTranslationY);

        mCameraView.setTranslationY(translation);
        mBottomView.setTranslationY(translation);
        mBottomView.setAlpha(1 - fractionFromCenter);
    }

    private void moveViews(float fractionFromFromCentre) {
        mStartView.setTranslationX(fractionFromFromCentre * mEndViewsTranslation);
        mEndView.setTranslationX(-fractionFromFromCentre * mEndViewsTranslation);

        mIndicator.setAlpha(fractionFromFromCentre);
        mIndicator.setScaleX(fractionFromFromCentre);
    }

    private void setColor(float fractionFromCenter) {
        int color = (int) argbEvaluator.evaluate(fractionFromCenter, centerColor, endsColor);
        mCameraView.setColorFilter(color);
        mStartView.setColorFilter(color);
        mEndView.setColorFilter(color);
    }
}
