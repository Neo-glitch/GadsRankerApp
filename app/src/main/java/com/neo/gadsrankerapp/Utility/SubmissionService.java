package com.neo.gadsrankerapp.Utility;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface SubmissionService {

    @POST
    Call<Response> submitResult(@Url String altUrl);
}
