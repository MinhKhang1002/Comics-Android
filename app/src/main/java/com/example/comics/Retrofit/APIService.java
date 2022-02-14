package com.example.comics.Retrofit;

import com.example.comics.Models.Book;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface APIService {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    APIService apiService  = new Retrofit.Builder()
            .baseUrl("https://one-read.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(APIService.class);

    @GET("book/top-view-day")
    Call<Book> getListBook1();


}
