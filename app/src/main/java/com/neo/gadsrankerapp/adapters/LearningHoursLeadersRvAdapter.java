package com.neo.gadsrankerapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.neo.gadsrankerapp.R;
import com.neo.gadsrankerapp.models.TopLearnerHours;

import java.util.ArrayList;
import java.util.List;

public class LearningHoursLeadersRvAdapter extends RecyclerView.Adapter<LearningHoursLeadersRvAdapter.MyViewHolder>{
    private List<TopLearnerHours> mTopLearners;
    private Context mContext;



    public LearningHoursLeadersRvAdapter(Context context, List<TopLearnerHours> topLearners){
        this.mTopLearners = topLearners;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_learning_leader, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TopLearnerHours topLearner = mTopLearners.get(position);

        holder.userName.setText(topLearner.getName());
        holder.learningHours.setText(topLearner.getHours() + " learning hours, Kenya");
    }

    @Override
    public int getItemCount() {
        return mTopLearners.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView userName, learningHours;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.tv_name);
            learningHours = itemView.findViewById(R.id.tv_learning_hours);
        }
    }
}
