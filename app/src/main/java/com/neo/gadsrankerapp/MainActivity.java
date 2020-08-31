package com.neo.gadsrankerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.neo.gadsrankerapp.Utility.ServiceBuilder;
import com.neo.gadsrankerapp.Utility.TopLearnersService;
import com.neo.gadsrankerapp.adapters.MyPagerAdapter;
import com.neo.gadsrankerapp.models.TopLearnerHours;
import com.neo.gadsrankerapp.models.TopLearnerHoursTest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        testThings();
    }

    private void testThings() {
        TopLearnersService service = ServiceBuilder.buildService(TopLearnersService.class);
        // creates request that calls getsLearningLeaders in service interface
        final Call<List<TopLearnerHours>>request =service.getLearningLeaders();

        request.enqueue(new Callback<List<TopLearnerHours>>() {
            @Override
            public void onResponse(Call<List<TopLearnerHours>> call, Response<List<TopLearnerHours>> response) {
                if(response.isSuccessful()){
//                    Log.d(TAG, "onResponse: " + response);
                    List<TopLearnerHours> result = response.body();
                    for(TopLearnerHours topLearners : result){
//                        Log.d(TAG, "onResponse: " + topLearners.toString());
                    }
                }

            }

            @Override
            public void onFailure(Call<List<TopLearnerHours>> call, Throwable t) {

            }
        });
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

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, SubmitActivity.class));
    }
}