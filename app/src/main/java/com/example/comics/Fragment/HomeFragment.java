package com.example.comics.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import com.example.comics.Models.Response;
import com.example.comics.R;
import com.example.comics.Retrofit.IBookAPI;
import com.example.comics.Retrofit.RetrofitClient;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class HomeFragment extends Fragment {
    Book book = new Book();
    Response<Book> listBook = new Response<>();
    SliderView sliderView;
    View view;
    IBookAPI iBookAPI;
    List<Book> list = new ArrayList<>();

    int[] images = {R.drawable.slider1, R.drawable.slider2, R.drawable.slider3, R.drawable.slider4};
    RecyclerView recyclerViewBook, recyclerViewTopMonth,recyclerViewTopRating,recyclerViewYear;
    BookAdapter bookAdapter,bookAdapterTopRating;
    BookAdapter3 bookAdapterTopViewMonth,bookAdapterTopViewYear;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);
        sliderView = view.findViewById(R.id.imageSlider);
       // Slider();
        recyclerViewBook = view.findViewById(R.id.recyclerViewHome);
        recyclerViewTopMonth = view.findViewById(R.id.recyclerViewHome2);
        recyclerViewTopRating = view.findViewById(R.id.recyclerViewTopRating);
        recyclerViewYear = view.findViewById(R.id.recyclerViewYear);

        bookAdapter = new BookAdapter(view.getContext());
        bookAdapterTopRating = new BookAdapter(view.getContext());
        bookAdapterTopViewMonth = new BookAdapter3(view.getContext());
        bookAdapterTopViewYear = new BookAdapter3(view.getContext());

        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(view.getContext(), 2);
        recyclerViewBook.setLayoutManager(gridLayoutManager2);

        GridLayoutManager gridLayoutManager3 = new GridLayoutManager(view.getContext(),3);
        recyclerViewTopMonth.setLayoutManager(gridLayoutManager3);



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewTopRating.setLayoutManager(linearLayoutManager);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(view.getContext());
        linearLayoutManager1.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewYear.setLayoutManager(linearLayoutManager1);

        getTopViewDay();
        getTopViewMonth();
        getTopRating();
        getTopViewYear();



        return view;
    }


    private void getTopViewDay(){
        RetrofitClient.getAPI().getTopViewDay().enqueue(new Callback<Response<Book>>() {
            @Override
            public void onResponse(Call<Response<Book>> call, retrofit2.Response<Response<Book>> response) {
                List<Book> listTopViewDay = new ArrayList<>();
                listBook = response.body();
                listTopViewDay = listBook.getData();
                bookAdapter.setData(listTopViewDay);
                recyclerViewBook.setAdapter(bookAdapter);
            }

            @Override
            public void onFailure(Call<Response<Book>> call, Throwable t) {
                Toast.makeText(getContext(), "Call Api Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getTopViewMonth(){
        RetrofitClient.getAPI().getTopViewMonth().enqueue(new Callback<Response<Book>>() {
            @Override
            public void onResponse(Call<Response<Book>> call, retrofit2.Response<Response<Book>> response) {
                List<Book> listTopViewMonth = new ArrayList<>();
                listBook = response.body();
                listTopViewMonth = listBook.getData();
                listTopViewMonth.remove(9);
                bookAdapterTopViewMonth.setData(listTopViewMonth);
                recyclerViewTopMonth.setAdapter(bookAdapterTopViewMonth);
            }

            @Override
            public void onFailure(Call<Response<Book>> call, Throwable t) {

            }
        });
    }

    public void getTopRating(){

        RetrofitClient.getAPI().getTopRating().enqueue(new Callback<Response<Book>>() {
            @Override
            public void onResponse(Call<Response<Book>> call, retrofit2.Response<Response<Book>> response) {
                List<Book> listTopRating = new ArrayList<>();
                listBook = response.body();
                listTopRating = listBook.getData();
                bookAdapterTopRating.setData(listTopRating);
                recyclerViewTopRating.setAdapter(bookAdapterTopRating);
            }

            @Override
            public void onFailure(Call<Response<Book>> call, Throwable t) {
                Toast.makeText(getContext(), "Call Api Failed", Toast.LENGTH_SHORT).show();
            }
        });


    }
    public void getTopViewYear(){
            RetrofitClient.getAPI().getTopViewYear().enqueue(new Callback<Response<Book>>() {
                @Override
                public void onResponse(Call<Response<Book>> call, retrofit2.Response<Response<Book>> response) {
                    List<Book> listTopViewYear = new ArrayList<>();
                    listBook = response.body();
                    listTopViewYear = listBook.getData();
                    bookAdapterTopViewYear.setData(listTopViewYear);
                    recyclerViewYear.setAdapter(bookAdapterTopViewYear);
                }

                @Override
                public void onFailure(Call<Response<Book>> call, Throwable t) {
                    Toast.makeText(getContext(), "Call Api Failed", Toast.LENGTH_SHORT).show();
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
