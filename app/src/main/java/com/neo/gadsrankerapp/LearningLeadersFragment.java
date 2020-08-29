package com.neo.gadsrankerapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neo.gadsrankerapp.adapters.LearningHoursLeadersRvAdapter;
import com.neo.gadsrankerapp.models.TopLearnerHours;

import java.util.ArrayList;

/**
 * fragment class holding the topLearners by hours
 */
public class LearningLeadersFragment extends Fragment {
    private static final String TAG = "LearningLeadersFragment";

    // widgets
    RecyclerView mRecyclerView;

    // vars
    private ArrayList<TopLearnerHours> mTopLearners;
    private LearningHoursLeadersRvAdapter mAdapter;



    public LearningLeadersFragment() {
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
        View view = inflater.inflate(R.layout.fragment_learning_leaders, container, false);

        mRecyclerView = view.findViewById(R.id.recycler_view_hours);
        mTopLearners = new ArrayList<>();
        initRecyclerView();

        return view;
    }

    private void initRecyclerView() {
        mTopLearners.add(new TopLearnerHours("james", 300));
        mTopLearners.add(new TopLearnerHours("marcelo", 400));
        mTopLearners.add(new TopLearnerHours("oge", 100));
        mTopLearners.add(new TopLearnerHours("ghost", 480));
        mTopLearners.add(new TopLearnerHours("badoo", 900));

        mAdapter = new LearningHoursLeadersRvAdapter(getContext(), mTopLearners);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

    }
}