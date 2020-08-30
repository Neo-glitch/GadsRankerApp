package com.neo.gadsrankerapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.neo.gadsrankerapp.Utility.ServiceBuilder;
import com.neo.gadsrankerapp.Utility.TopLearnersService;
import com.neo.gadsrankerapp.adapters.SkillIQLeadersRvAdapter;
import com.neo.gadsrankerapp.models.TopLearnerSkill;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * fragment class housing top learners by skill IQ assessment
 */
public class SkillLeadersFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private static final String TAG = "SkillLeadersFragment";

    //widgets
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    //vars
    private List<TopLearnerSkill> mTopLearners;
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
        Log.d(TAG, "onCreateView: starts");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_skill_leaders, container, false);

        mRecyclerView = view.findViewById(R.id.recycler_view_skill);
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout_skill);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mTopLearners = new ArrayList<>();
        getSkillLeaders();
        initRecylerView();

        return view;
    }

    private void getSkillLeaders() {
        TopLearnersService service = ServiceBuilder.buildService(TopLearnersService.class);
        Call<List<TopLearnerSkill>> request = service.getSkillLeaders();

        request.enqueue(new Callback<List<TopLearnerSkill>>() {
            @Override
            public void onResponse(Call<List<TopLearnerSkill>> call, Response<List<TopLearnerSkill>> response) {
                if(response.code() == 200 || response.isSuccessful()){
                    mTopLearners.addAll(response.body());
                    mAdapter.notifyDataSetChanged();
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<List<TopLearnerSkill>> call, Throwable t) {
                if(t instanceof IOException){
                    Toast.makeText(getContext(), "Please check your internet connection", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Failed to retreive items", Toast.LENGTH_SHORT).show();
                }
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void initRecylerView() {
        mAdapter = new SkillIQLeadersRvAdapter(getContext(), mTopLearners);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onRefresh() {
        getSkillLeaders();
    }
}