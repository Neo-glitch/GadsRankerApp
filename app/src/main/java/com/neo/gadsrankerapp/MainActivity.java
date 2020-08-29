package com.neo.gadsrankerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.neo.gadsrankerapp.adapters.MyPagerAdapter;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private static final int LEARNING_LEADERS_FRAGMENT = 0;
    private static final int SKILL_LEADERS_FRAGMENT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = findViewById(R.id.view_pager);

        initViewPager();
    }


    // initializes viewPager with needed fragments
    private void initViewPager() {
        LearningLeadersFragment learningLeadersFragment = new LearningLeadersFragment();
        SkillLeadersFragment skillLeadersFragment = new SkillLeadersFragment();

        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        myPagerAdapter.addFragment(learningLeadersFragment);
        myPagerAdapter.addFragment(skillLeadersFragment);
        mViewPager.setAdapter(myPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(mViewPager);

        tabLayout.getTabAt(LEARNING_LEADERS_FRAGMENT).setText("Learning Leaders");
        tabLayout.getTabAt(SKILL_LEADERS_FRAGMENT).setText("Skill IQ Leaders");


    }
}