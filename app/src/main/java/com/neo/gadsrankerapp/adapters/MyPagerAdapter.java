package com.neo.gadsrankerapp.adapters;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * custom ViewPager Adapter Class
 */
public class MyPagerAdapter extends FragmentPagerAdapter {

    // list of fragments
    private ArrayList<Fragment> mFragments= new ArrayList<>();

    public MyPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    /**
     * adds the fragment needed to the fragment list populating ViewPager
     */
    public void addFragment(Fragment newFragment){
        mFragments.add(newFragment);
    }
}
