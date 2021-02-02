package com.bsoft.signupprototype.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.bsoft.signupprototype.Adapters.TabAdapter;
import com.bsoft.signupprototype.Main;
import com.bsoft.signupprototype.R;
import com.bsoft.signupprototype.fragments.LoginFragments.LoginUsername;
import com.bsoft.signupprototype.fragments.LoginFragments.LoginUID;
import com.google.android.material.tabs.TabLayout;

public class Login extends BaseFragmet {
    TabLayout mTabs;
    View mIndicator;
    ViewPager mViewPager;
    int indicatorWidth = 0;

    public Login(Main parent) {
        super(parent);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTabs = view.findViewById(R.id.tab);
        mIndicator = view.findViewById(R.id.indicator);
        mViewPager = view.findViewById(R.id.viewPager);

        //Set up the view pager and fragments
        TabAdapter adapter = new TabAdapter(getChildFragmentManager());
        adapter.addFragment(new LoginUID(parent), "UID");
        adapter.addFragment(new LoginUsername(parent), "Username");
        mViewPager.setAdapter(adapter);
        mTabs.setupWithViewPager(mViewPager);

        //Determine indicator width at runtime
        mTabs.post(new Runnable() {
            @Override
            public void run() {
                indicatorWidth = mTabs.getWidth() / mTabs.getTabCount();

                //Assign new width
                RelativeLayout.LayoutParams indicatorParams = (RelativeLayout.LayoutParams) mIndicator.getLayoutParams();
                indicatorParams.width = indicatorWidth;
                mIndicator.setLayoutParams(indicatorParams);
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float positionOffset, int positionOffsetPx) {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)mIndicator.getLayoutParams();

                float translationOffset =  (positionOffset+i) * indicatorWidth ;
                params.leftMargin = (int) translationOffset;
                mIndicator.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}
