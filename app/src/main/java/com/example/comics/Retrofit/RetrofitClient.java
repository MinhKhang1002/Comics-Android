package com.example.comics.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {
    public static Retrofit retrofit;


    public static Retrofit getInstance(){

        if(retrofit==null){
            retrofit= new Retrofit.Builder()
                    .baseUrl("https://one-read-v2.herokuapp.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    public static IBookAPI getAPI(){
        return RetrofitClient.getInstance().create(IBookAPI.class);
    }
}
