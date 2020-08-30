package com.neo.gadsrankerapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.neo.gadsrankerapp.Utility.ServiceBuilder;
import com.neo.gadsrankerapp.Utility.TopLearnersService;
import com.neo.gadsrankerapp.adapters.LearningHoursLeadersRvAdapter;
import com.neo.gadsrankerapp.models.TopLearnerHours;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * fragment class holding the topLearners by hours
 */
public class LearningLeadersFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private static final String TAG = "LearningLeadersFragment";
    public static final String ARRAY_TOP_LEARNERS = "ArrayTopLearners";

    // widgets
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    // vars
    private List<TopLearnerHours> mTopLearners;
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
        Log.d(TAG, "onCreateView: starts");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_learning_leaders, container, false);

        mRecyclerView = view.findViewById(R.id.recycler_view_hours);
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout_learning);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        if(savedInstanceState != null){
            mTopLearners = savedInstanceState.getParcelableArrayList(ARRAY_TOP_LEARNERS);
        } else{
            mTopLearners = new ArrayList<>();
            getTopLearningLeaders();
        }
        initRecyclerView();

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList("ArrayTopLearners", (ArrayList<? extends Parcelable>) mTopLearners);
        super.onSaveInstanceState(outState);
    }

    private void getTopLearningLeaders() {
        TopLearnersService service = ServiceBuilder.buildService(TopLearnersService.class);
        // creates request that calls getsLearningLeaders in service interface
        final Call<List<TopLearnerHours>> request =service.getLearningLeaders();

        request.enqueue(new Callback<List<TopLearnerHours>>() {
            @Override
            public void onResponse(Call<List<TopLearnerHours>> call, Response<List<TopLearnerHours>> response) {
                if(response.code() == 200 || response.isSuccessful()){
                    mTopLearners.addAll(response.body());
                    mAdapter.notifyDataSetChanged();
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<List<TopLearnerHours>> call, Throwable t) {
                if(t instanceof IOException){
                    Toast.makeText(getContext(), "Please check your internet connection and refresh", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Failed to retreive items", Toast.LENGTH_SHORT).show();
                }
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void initRecyclerView() {
        mAdapter = new LearningHoursLeadersRvAdapter(getContext(), mTopLearners);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onRefresh() {
        getTopLearningLeaders();
    }
}