package com.example.comics.Retrofit;

import com.example.comics.Models.books;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IBookAPI
{
    @GET("/book/top-view-day")
    Call<List<books>> getListBook();

    @GET("")
    Call<books> getTop10();

}
