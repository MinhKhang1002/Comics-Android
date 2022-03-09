package com.example.comics.Retrofit;

import com.example.comics.Models.Book;
import com.example.comics.Models.Chapter;
import com.example.comics.Models.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;

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

    @GET("/book/top-search")
    Call<Response<Book>> getTopSearch();

    @GET("/book/last-update")
    Call<Response<Book>> getLastUpdate();

    @GET("/book/top-follow")
    Call<Response<Book>> getTopFollow();

    @GET("/book/detail/{book_endpoint}")
    Call<Response<Book>> getBook(@Path("book_endpoint") String endpoint);

    @GET("/chapter/all/{book_endpoint}")
    Call<Response<Chapter>> getAllChapter(@Path("book_endpoint")String endpoint);

    @GET("/book/relate-book/{endpoint}")
    Call<Response<Book>> getRelateBook(@Path("endpoint") String endpoint);

    @GET("/chapter/detail/grand-blue/chapter-74?view=true")
    Call<Response<Chapter>> getChapter();


}
