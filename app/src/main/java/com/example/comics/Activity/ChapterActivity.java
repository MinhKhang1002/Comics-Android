package com.example.comics.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.comics.Adapter.ChapterAdapter;
import com.example.comics.Models.Chapter;
import com.example.comics.Models.Response;
import com.example.comics.R;
import com.example.comics.Retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class ChapterActivity extends AppCompatActivity {
    Response<Chapter> responseChapter = new Response<>();
    List<Chapter> listChapter = new ArrayList<>();
    List<String> listImages = new ArrayList<>();
    RecyclerView recyclerViewChapter;
    ChapterAdapter chapterAdapter = new ChapterAdapter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);
        recyclerViewChapter = findViewById(R.id.recycleChapter);
        Log.d("tesss",listImages.size()+"");
        getChapter();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewChapter.setLayoutManager(linearLayoutManager);
        recyclerViewChapter.setAdapter(chapterAdapter);



    }

    public void getChapter(){
        RetrofitClient.getAPI().getChapter().enqueue(new Callback<Response<Chapter>>() {
            @Override
            public void onResponse(Call<Response<Chapter>> call, retrofit2.Response<Response<Chapter>> response) {
                responseChapter = response.body();
                listChapter = responseChapter.getData();
                Log.d("tesaa",listChapter.get(0).getImages()+"");
                listImages = listChapter.get(0).getImages();
                Log.d("tesaa1",listImages.size()+"");
                chapterAdapter.SetData(listImages);
                Log.d("tessa2",chapterAdapter.getItemCount()+"");

            }

            @Override
            public void onFailure(Call<Response<Chapter>> call, Throwable t) {

            }
        });
    }
}