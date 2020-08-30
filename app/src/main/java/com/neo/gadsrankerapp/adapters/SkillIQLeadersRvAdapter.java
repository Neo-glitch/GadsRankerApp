package com.neo.gadsrankerapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.neo.gadsrankerapp.R;
import com.neo.gadsrankerapp.models.TopLearnerSkill;

import java.util.ArrayList;
import java.util.List;

public class SkillIQLeadersRvAdapter extends RecyclerView.Adapter<SkillIQLeadersRvAdapter.MyViewHolder> {
    private Context mContext;
    private List<TopLearnerSkill> mTopLearners;

    public SkillIQLeadersRvAdapter(Context context, List<TopLearnerSkill> topLearners) {
        mContext = context;
        mTopLearners = topLearners;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_skill_iq_leader, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TopLearnerSkill topLearner = mTopLearners.get(position);
        holder.userName.setText(topLearner.getName());
        holder.score.setText(topLearner.getScore() + " skill IQ score, Egypt");

    }

    @Override
    public int getItemCount() {
        return mTopLearners.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView userName, score;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.tv_name);
            score = itemView.findViewById(R.id.tv_score);
        }
    }
}
