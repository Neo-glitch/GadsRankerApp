package com.neo.gadsrankerapp.Utility;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * class to implement retrofit Builder
 */
public class ServiceBuilder {
    private static final String BASE_URL = "https://gadsapi.herokuapp.com";

    // to configure the retrofitInstance
    private static Retrofit.Builder sBuilder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    // create the retrofit instance
    private static Retrofit sRetrofit = sBuilder.build();

    // creates API implementation of th service interface(LearningLeaders Service or SkillLeaders service)
    public static <S> S buildService(Class<S> serviceType){
        return sRetrofit.create(serviceType);
    }


}
