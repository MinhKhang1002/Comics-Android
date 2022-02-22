package com.example.comics.Retrofit;

import com.example.comics.Models.Book;
import com.example.comics.Models.Response;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IBookAPI
{
    @GET("/book/top-view-day")
    Call<Response<Book>> getTopViewDay();

    @GET("/book/top-view-month")
    Call<Response<Book>> getTopViewMonth();

    @GET("/book/top-rating")
    Call<Response<Book>> getTopRating();

    @GET("/book/top-view-year")
    Call<Response<Book>> getTopViewYear();



}
