package com.example.comics.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comics.Adapter.BookAdapter;
import com.example.comics.Adapter.SliderAdapter;
import com.example.comics.Models.Book;
import com.example.comics.Models.books;
import com.example.comics.R;
import com.example.comics.Retrofit.APIService;
import com.example.comics.Retrofit.IBookAPI;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    SliderView sliderView;
    View view;
    IBookAPI iBookAPI;
    List<books> list = new ArrayList<>();
    int[] images = {R.drawable.slider1, R.drawable.slider2, R.drawable.slider3, R.drawable.slider4};
    RecyclerView recyclerViewBook, recyclerViewBook2;
    BookAdapter bookAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);
        sliderView = view.findViewById(R.id.imageSlider);
        Slider();
        recyclerViewBook = view.findViewById(R.id.recyclerViewHome);
        bookAdapter = new BookAdapter(view.getContext());

        getListBook2();

        return view;
    }

    private void getListBook2(){
        List<books> mlist = new ArrayList<>();
        APIService.apiService.getListBook().enqueue(new Callback<Book>() {

            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                Book book = new Book();

                book = response.body();

                GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 2);
                recyclerViewBook.setLayoutManager(gridLayoutManager);
                bookAdapter.setData(book.getBooks());
                Log.d("why","aaa"+book.getBooks().get(1).getTheme());
                recyclerViewBook.setAdapter(bookAdapter);


            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {

            }
        });

    }


    private List<books> getListBook() {
        List<books> list = new ArrayList<>();
        list.add(new books("3123", "Chú Bé Rồng - Ryuuroden", "djksa", "https://st.nettruyengo.com/data/comics/122/ryuuroden-chu-be-rong.jpg", "ddd", "3213", "dsadsad", 2, 3, 1, 2, 3));
        list.add(new books("23321", "Tu Tiên Trở Về Tại Vườn Trường", "djksa", "https://st.nettruyengo.com/data/comics/203/deatte-5-byou-de-battle.jpg", "https://st.nettruyengo.com/data/comics/122/ryuuroden-chu-be-rong.jpg", "3213", "dsadsad", 2, 3, 1, 2, 3));
        list.add(new books("23331", "Khởi Tạo Nhân Vật Phản Diện", "djksa", "https://st.nettruyengo.com/data/comics/122/ryuuroden-chu-be-rong.jpg", "ddd", "3213", "dsadsad", 2, 3, 1, 2, 3));
        list.add(new books("23321", "Tu Chân Nói Chuyện Phiếm Quần", "djksa", "https://st.nettruyengo.com/data/comics/121/vu-em-la-co-tien.jpg", "ddd", "3213", "dsadsad", 2, 3, 1, 2, 3));


        return list;
    }


    public void Slider() {
        SliderAdapter sliderAdapter = new SliderAdapter(images);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();
    }
}
