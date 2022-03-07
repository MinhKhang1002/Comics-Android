package com.example.comics.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.comics.Models.Book;
import com.example.comics.Models.Chapter;
import com.example.comics.Models.Response;
import com.example.comics.R;
import com.example.comics.Retrofit.RetrofitClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class DetailBookActivity extends AppCompatActivity {
    Book book = new Book();
    List<Book> listBook = new ArrayList<>();
    Response<Book> reponseBook = new Response<>();
    Response<Chapter> reponseChapter = new Response<>();
    TextView tvTittle
            ,tvAuthor
            ,tvGenre
            ,tvGenre1
            ,tvGenre2
            ,tvScoreRating
            ,tvDesc
            ,tvReadMore;
    ImageView imageView,btn_go_back;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_book);
        AnhXa();
        book.setEndpoint(getIntent().getStringExtra("endpoint"));
        test();
        getDetailBook();
        goBack();
        readMore();

    }
    public void readMore(){
        tvReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvDesc.setEllipsize(null);
                tvDesc.setMaxLines(Integer.MAX_VALUE);
                tvReadMore.setVisibility(View.GONE);
            }
        });
    }
    public void goBack(){
        btn_go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void AnhXa(){
        tvTittle = findViewById(R.id.tvTittle);
        tvAuthor = findViewById(R.id.tvAuthor);
        imageView = findViewById(R.id.imageViewDetail);
        tvGenre = findViewById(R.id.tvGenre);
        tvGenre1 = findViewById(R.id.tvGenre1);
        tvGenre2 = findViewById(R.id.tvGenre2);
        ratingBar = findViewById(R.id.ratingBar);
        tvScoreRating = findViewById(R.id.tvScoreRating);
        tvDesc = findViewById(R.id.tvDesc);
        btn_go_back = findViewById(R.id.btn_go_back);
        tvReadMore = findViewById(R.id.tvReadMore);
    }


    public void getDetailBook(){
        RetrofitClient.getAPI().getBook(book.getEndpoint()).enqueue(new Callback<Response<Book>>() {
            @Override
            public void onResponse(Call<Response<Book>> call, retrofit2.Response<Response<Book>> response) {
                reponseBook= response.body();
                listBook = reponseBook.getData();
                tvTittle.setText(listBook.get(0).getTitle());
                tvAuthor.setText(listBook.get(0).getAuthor());
                tvGenre.setText(listBook.get(0).getGenres().get(0).getTitle());
                tvGenre1.setText(listBook.get(0).getGenres().get(1).getTitle());
                tvGenre2.setText(listBook.get(0).getGenres().get(2).getTitle());
                ratingBar.setRating(4.5F);
                tvScoreRating.setText(listBook.get(0).getRating()+"");
                tvDesc.setText(listBook.get(0).getDescription());

                Picasso.get().load(listBook.get(0).getThumb()).placeholder(R.drawable.image_loading).error(R.drawable.image_err).into(imageView);
            }

            @Override
            public void onFailure(Call<Response<Book>> call, Throwable t) {
                Toast.makeText(DetailBookActivity.this, "Call API failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void test(){
        RetrofitClient.getAPI().getAllChapter(book.getEndpoint()).enqueue(new Callback<Response<Chapter>>() {
            @Override
            public void onResponse(Call<Response<Chapter>> call, retrofit2.Response<Response<Chapter>> response) {
                reponseChapter = response.body();

               // Toast.makeText(DetailBookActivity.this, reponseChapter.getData().get(0).getChapter_endpoint(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Response<Chapter>> call, Throwable t) {

            }
        });
    }
}