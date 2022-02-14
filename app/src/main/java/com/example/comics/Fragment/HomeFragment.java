package com.example.comics.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comics.Adapter.BookAdapter;
import com.example.comics.Adapter.BookAdapter3;
import com.example.comics.Adapter.SliderAdapter;
import com.example.comics.Models.Book;
import com.example.comics.R;
import com.example.comics.Retrofit.APIService;
import com.example.comics.Retrofit.IBookAPI;
import com.example.comics.Retrofit.RetrofitClient;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment {
    Book book = new Book();
    SliderView sliderView;
    View view;
    IBookAPI iBookAPI;
    List<Book> list = new ArrayList<>();
    int[] images = {R.drawable.slider1, R.drawable.slider2, R.drawable.slider3, R.drawable.slider4};
    RecyclerView recyclerViewBook, recyclerViewBook2;
    BookAdapter bookAdapter;
    BookAdapter3 bookAdapter3;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);
        sliderView = view.findViewById(R.id.imageSlider);
       // Slider();
        recyclerViewBook = view.findViewById(R.id.recyclerViewHome);
        recyclerViewBook2 = view.findViewById(R.id.recyclerViewHome2);

        bookAdapter = new BookAdapter(view.getContext());
        bookAdapter3 = new BookAdapter3(view.getContext());


        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(view.getContext(), 2);
        recyclerViewBook.setLayoutManager(gridLayoutManager2);
        getTopViewDay();
        getTopViewMonth();

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
//        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);

        GridLayoutManager gridLayoutManager3 = new GridLayoutManager(view.getContext(),3);

//        bookAdapter.setData(getListBookT());
        recyclerViewBook2.setLayoutManager(gridLayoutManager3);




        return view;
    }


    private void getTopViewDay(){
        RetrofitClient.getAPI().getTopViewDay().enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                List<Book> listTopViewDay = new ArrayList<>();

                book = response.body();
                listTopViewDay = book.getBooks();


                bookAdapter.setData(listTopViewDay);

                recyclerViewBook.setAdapter(bookAdapter);
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {

            }
        });
    }

    public void getTopViewMonth(){
        RetrofitClient.getAPI().getTopViewMonth().enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                List<Book> listTopViewMonth = new ArrayList<>();

                book = response.body();
                listTopViewMonth = book.getBooks();
                listTopViewMonth.remove(9);


                bookAdapter3.setData(listTopViewMonth);

                recyclerViewBook2.setAdapter(bookAdapter3);
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {

            }
        });
    }

    public void Slider() {

        SliderAdapter sliderAdapter = new SliderAdapter(images);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();
    }
}
