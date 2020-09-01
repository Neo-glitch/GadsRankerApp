package com.neo.gadsrankerapp.Utility;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface SubmissionService {



    @POST
    @FormUrlEncoded
    Call<ResponseBody> submitResult(     // ret val is void since result obj not relevant
            @Url String altUrl,
            @Field("entry.1824927963") String emailAddress,
            @Field("entry.1877115667") String name,
            @Field("entry.2006916086") String lastName,
            @Field("entry.284483984") String LinkToProject
    );
}
