package com.neo.gadsrankerapp.Utility;

import com.neo.gadsrankerapp.models.TopLearnerHours;
import com.neo.gadsrankerapp.models.TopLearnerSkill;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TopLearnersService {
    // gets top learners based on hours put in course
    @GET("/api/hours")
    Call<List<TopLearnerHours>> getLearningLeaders();

    @GET("/api/skilliq")
    Call<List<TopLearnerSkill>> getSkillLeaders();
}
