package com.mannysight.saybaba;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mannysight.saybaba.adapter.MainPagerAdapter;
import com.mannysight.saybaba.view.AnimTabsView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnimTabsView animTabsView = findViewById(R.id.animTab);
        final View background = findViewById(R.id.am_background_view);
        ViewPager viewPager = findViewById(R.id.am_view_pager);
        MainPagerAdapter pagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        animTabsView.setUpWithViewPager(viewPager);
//        viewPager.setCurrentItem(1);

        final int colorBlue = ContextCompat.getColor(this, R.color.light_blue);
        final int colorPurple = ContextCompat.getColor(this, R.color.light_purple);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0) {
                    background.setBackgroundColor(colorBlue);
                    background.setAlpha(1 - positionOffset);
                } else if (position == 1) {
                    background.setBackgroundColor(colorPurple);
                    background.setAlpha(positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }
}