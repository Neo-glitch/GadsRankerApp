package com.neo.gadsrankerapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neo.gadsrankerapp.adapters.SkillIQLeadersRvAdapter;
import com.neo.gadsrankerapp.models.TopLearnerSkill;

import java.util.ArrayList;


/**
 * fragment class housing top learners by skill IQ assessment
 */
public class SkillLeadersFragment extends Fragment {
    private static final String TAG = "SkillLeadersFragment";

    //widgets
    RecyclerView mRecyclerView;

    //vars
    private ArrayList<TopLearnerSkill> mTopLearners;
    private SkillIQLeadersRvAdapter mAdapter;


    public SkillLeadersFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_skill_leaders, container, false);

        mRecyclerView = view.findViewById(R.id.recycler_view_skill);
        mTopLearners = new ArrayList<>();
        initRecylerView();

        return view;
    }

    private void initRecylerView() {
        mTopLearners.add(new TopLearnerSkill("mitch Tabian", 100));
        mTopLearners.add(new TopLearnerSkill("Jim", 200));
        mTopLearners.add(new TopLearnerSkill("Jane", 500));
        mTopLearners.add(new TopLearnerSkill("Okon", 110));
        mTopLearners.add(new TopLearnerSkill("Bobo", 10));
        mTopLearners.add(new TopLearnerSkill("Messi", 105));

        mAdapter = new SkillIQLeadersRvAdapter(getContext(), mTopLearners);
        Log.d(TAG, "initRecylerView: " + mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

    }
}