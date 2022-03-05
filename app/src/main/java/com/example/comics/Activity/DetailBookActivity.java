package com.example.comics.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.comics.Models.Book;
import com.example.comics.R;

public class DetailBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_book);
        Book book = new Book();
        book.setEndpoint(getIntent().getStringExtra("endpoint"));
        Log.d("Test",book.getEndpoint());
    }
}