package com.example.comics.Retrofit;

import com.example.comics.Models.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IBookAPI
{
    @GET("/book/top-view-day")
    Call<Book> getTopViewDay();

    @GET("/book/top-view-month")
    Call<Book> getTopViewMonth();



}
