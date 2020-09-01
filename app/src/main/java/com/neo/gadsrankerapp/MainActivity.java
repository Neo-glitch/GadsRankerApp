package com.neo.gadsrankerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.neo.gadsrankerapp.adapters.MyPagerAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    // constants
    private static final String TAG = "MainActivity";
    private static final int LEARNING_LEADERS_FRAGMENT = 0;
    private static final int SKILL_LEADERS_FRAGMENT = 1;


    //widget
    private Window mWindow;
    private ViewPager mViewPager;
    private TextView mSubmitTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = findViewById(R.id.view_pager);
        mSubmitTv = findViewById(R.id.submit_tv);
        mSubmitTv.setOnClickListener(this);

        // sets activity layout to fullScreen and enables transparent statusBar implementation
        mWindow = getWindow();
        mWindow.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

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

        tabLayout.getTabAt(LEARNING_LEADERS_FRAGMENT).setText(R.string.learning_leaders_frag_name);
        tabLayout.getTabAt(SKILL_LEADERS_FRAGMENT).setText(R.string.skill_leaders_frag_name);


    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, SubmitActivity.class));
    }
}