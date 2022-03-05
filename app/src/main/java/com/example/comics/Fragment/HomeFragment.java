package com.example.comics.Fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
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

import java.io.Serializable;
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
    List<Book> listTopDay = new ArrayList<>();
    List<Book> listTopMonth = new ArrayList<>();
    List<Book> listTopYear = new ArrayList<>();
    List<Book> listTopRating = new ArrayList<>();
    List<Book> listTopSearch = new ArrayList<>();
    List<Book> listLastUpdate = new ArrayList<>();
    List<Book> listTopFollow = new ArrayList<>();
//    private Parcelable state,state1,state2,state3;
//    private String keyBooksState = "booksState";
//    private String keyBooksState2 = "booksState2";
//    private String keyBooksState3 = "booksState3";
//    private String keyBooksState4 = "booksState4";
    private String keyStateScrollView = "scrollView";
    private String keyListDay="listDay";

    int[] images = {R.drawable.slider1, R.drawable.slider2  , R.drawable.slider3, R.drawable.slider4};

    RecyclerView recyclerViewBook
            , recyclerViewTopMonth
            ,recyclerViewTopRating
            ,recyclerViewYear
            ,recyclerViewTopSearch
            ,recyclerViewLastUpdate
            ,recyclerViewTopFollow;
    BookAdapter bookAdapter
            ,bookAdapterTopRating
            ,bookAdapterLastUpdate
            ,bookAdapterTopFollow;
    BookAdapter3 bookAdapterTopViewMonth
            ,bookAdapterTopViewYear
            ,bookAdapterTopSearch;
    ScrollView scrollView;
    int stateScrollView;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null){
          //  state = savedInstanceState.getParcelable(keyBooksState);
            listTopDay = (List<Book>) savedInstanceState.getSerializable(keyListDay);
//
//            state1 = savedInstanceState.getParcelable(keyBooksState2);
//            state2 = savedInstanceState.getParcelable(keyBooksState3);
//            state3 = savedInstanceState.getParcelable(keyBooksState4);
            stateScrollView = savedInstanceState.getInt(keyStateScrollView);

        }
    }

    @Override
    public void onResume() {


        super.onResume();
        scrollView.setVerticalScrollbarPosition(stateScrollView);


//        recyclerViewBook.getLayoutManager().onRestoreInstanceState(state);
//        recyclerViewTopMonth.getLayoutManager().onRestoreInstanceState(state1);
//        recyclerViewYear.getLayoutManager().onRestoreInstanceState(state2);
//        recyclerViewTopRating.getLayoutManager().onRestoreInstanceState(state3);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);
        AnhXa();
        Slider();
        if(listTopDay.size() ==0){
            getTopViewDay();
            getTopViewMonth();
            getTopRating();
            getTopViewYear();
            getTopSearch();
            getLastUpdate();
            getTopFollow();
        }
//        else{
//            //Toast.makeText(view.getContext(), "SAI SAI SAI", Toast.LENGTH_SHORT).show();
//        }
        initTopViewDay();
        initTopViewMonth();
        initTopViewYear();
        initTopViewTopRating();
        initTopSearch();
        initLastUpdate();
        initTopFollow();
        return view;
    }

    private void initTopViewTopRating() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewTopRating.setLayoutManager(linearLayoutManager);
        recyclerViewTopRating.setAdapter(bookAdapterTopRating);
        bookAdapterTopRating.setData(listTopRating);
    }

    private void initTopViewYear() {
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(view.getContext());
        linearLayoutManager1.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewYear.setLayoutManager(linearLayoutManager1);
        recyclerViewYear.setAdapter(bookAdapterTopViewYear);
        bookAdapterTopViewYear.setData(listTopYear);
    }

    private void initTopViewMonth() {
        GridLayoutManager gridLayoutManager3 = new GridLayoutManager(view.getContext(),3);
        recyclerViewTopMonth.setLayoutManager(gridLayoutManager3);
        recyclerViewTopMonth.setAdapter(bookAdapterTopViewMonth);
        bookAdapterTopViewMonth.setData(listTopMonth);
    }

    private void initTopViewDay() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 2);
        recyclerViewBook.setLayoutManager(gridLayoutManager);
        recyclerViewBook.setAdapter(bookAdapter);
        bookAdapter.setData(listTopDay);
    }

    private void initTopSearch() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 3);
        recyclerViewTopSearch.setLayoutManager(gridLayoutManager);
        recyclerViewTopSearch.setAdapter(bookAdapterTopSearch);
        bookAdapterTopSearch.setData(listTopSearch);
    }
    private void initLastUpdate() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewLastUpdate.setLayoutManager(linearLayoutManager);
        recyclerViewLastUpdate.setAdapter(bookAdapterLastUpdate);
        bookAdapterLastUpdate.setData(listLastUpdate);
    }

    private void initTopFollow() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewTopFollow.setLayoutManager(linearLayoutManager);
        recyclerViewTopFollow.setAdapter(bookAdapterTopFollow);
        bookAdapterTopFollow.setData(listTopFollow);

    }


    private void AnhXa() {
        sliderView = view.findViewById(R.id.imageSlider);
        recyclerViewBook = view.findViewById(R.id.recyclerViewHome);
        recyclerViewTopMonth = view.findViewById(R.id.recyclerTopMonth);
        recyclerViewTopRating = view.findViewById(R.id.recyclerViewTopRating);
        recyclerViewYear = view.findViewById(R.id.recyclerViewYear);
        recyclerViewTopSearch = view.findViewById(R.id.recyclerTopSearch);
        recyclerViewLastUpdate = view.findViewById(R.id.recycleViewLastUpdate);
        recyclerViewTopFollow = view.findViewById(R.id.recycleViewTopFollow);
        scrollView = view.findViewById(R.id.scrollView);

        
        bookAdapter = new BookAdapter(view.getContext());
        bookAdapterTopRating = new BookAdapter(view.getContext());
        bookAdapterTopViewMonth = new BookAdapter3(view.getContext());
        bookAdapterTopViewYear = new BookAdapter3(view.getContext());
        bookAdapterTopSearch = new BookAdapter3(view.getContext());
        bookAdapterLastUpdate = new BookAdapter(view.getContext());
        bookAdapterTopFollow = new BookAdapter(view.getContext());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putInt(keyStateScrollView,scrollView.getVerticalScrollbarPosition());
        outState.putInt("test",sliderView.getCurrentPagePosition());
        outState.putSerializable(keyListDay, (Serializable) listTopDay);
//        outState.putParcelable(keyBooksState,recyclerViewBook.getLayoutManager().onSaveInstanceState());
//        outState.putParcelable(keyBooksState2,recyclerViewTopMonth.getLayoutManager().onSaveInstanceState());
//        outState.putParcelable(keyBooksState3,recyclerViewYear.getLayoutManager().onSaveInstanceState());
//        outState.putParcelable(keyBooksState4,recyclerViewTopRating.getLayoutManager().onSaveInstanceState());


    }


    private void getTopViewDay(){
        RetrofitClient.getAPI().getTopViewDay().enqueue(new Callback<Response<Book>>() {
            @Override
            public void onResponse(Call<Response<Book>> call, retrofit2.Response<Response<Book>> response) {
                listBook = response.body();
                listTopDay = listBook.getData();
                listTopDay.remove(9);
                listTopDay.remove(8);

                bookAdapter.setData(listTopDay);

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
                listBook = response.body();
                listTopMonth = listBook.getData();
                listTopMonth.remove(9);
                bookAdapterTopViewMonth.setData(listTopMonth);
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
                listBook = response.body();
                listTopRating = listBook.getData();
                bookAdapterTopRating.setData(listTopRating);
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
                    listBook = response.body();
                    listTopYear = listBook.getData();
                    bookAdapterTopViewYear.setData(listTopYear);
                }

                @Override
                public void onFailure(Call<Response<Book>> call, Throwable t) {
                    Toast.makeText(getContext(), "Call Api Failed", Toast.LENGTH_SHORT).show();
                }
            });
    }

    public void getTopSearch(){
        RetrofitClient.getAPI().getTopSearch().enqueue(new Callback<Response<Book>>() {
            @Override
            public void onResponse(Call<Response<Book>> call, retrofit2.Response<Response<Book>> response) {
                listBook = response.body();
                listTopSearch = listBook.getData();
                listTopSearch.remove(9);
                bookAdapterTopSearch.setData(listTopSearch);
            }

            @Override
            public void onFailure(Call<Response<Book>> call, Throwable t) {
                Toast.makeText(getContext(), "Call Api Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getLastUpdate(){
        RetrofitClient.getAPI().getLastUpdate().enqueue(new Callback<Response<Book>>() {
            @Override
            public void onResponse(Call<Response<Book>> call, retrofit2.Response<Response<Book>> response) {
                listBook= response.body();
                listLastUpdate = listBook.getData();
                bookAdapterLastUpdate.setData(listLastUpdate);
            }

            @Override
            public void onFailure(Call<Response<Book>> call, Throwable t) {

            }
        });
    }

    public void getTopFollow(){
        RetrofitClient.getAPI().getTopFollow().enqueue(new Callback<Response<Book>>() {
            @Override
            public void onResponse(Call<Response<Book>> call, retrofit2.Response<Response<Book>> response) {
                listBook = response.body();
                listTopFollow = listBook.getData();
                bookAdapterTopFollow.setData(listTopFollow);
            }

            @Override
            public void onFailure(Call<Response<Book>> call, Throwable t) {

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
