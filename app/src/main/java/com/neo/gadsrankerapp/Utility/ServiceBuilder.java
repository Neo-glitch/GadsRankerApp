package com.neo.gadsrankerapp.Utility;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * class to implement retrofit Builder
 */
public class ServiceBuilder {
    // client to specify read timeOut
    private static OkHttpClient.Builder okHttp = new OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.SECONDS);

    private static final String BASE_URL = "https://gadsapi.herokuapp.com";

    // to configure the retrofitInstance
    private static Retrofit.Builder sBuilder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build());

    // create the retrofit instance
    private static Retrofit sRetrofit = sBuilder.build();

    // creates API implementation of th service interface(LearningLeaders Service or SkillLeaders service)
    public static <S> S buildService(Class<S> serviceType){
        return sRetrofit.create(serviceType);
    }


}
