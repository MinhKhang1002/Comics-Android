package com.example.comics.Fragment;

import android.os.Bundle;
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
import com.example.comics.MainActivity;
import com.example.comics.Models.Book;
import com.example.comics.R;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    SliderView sliderView;
    View view;
    int [] images = {R.drawable.slider1,R.drawable.slider2,R.drawable.slider3,R.drawable.slider4};
    RecyclerView recyclerViewBook,recyclerViewBook2;
    BookAdapter bookAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);
        sliderView = view.findViewById(R.id.imageSlider);
        Slider();
        recyclerViewBook = view.findViewById(R.id.recyclerViewHome);
        recyclerViewBook2 = view.findViewById(R.id.recyclerViewHome2);
        bookAdapter = new BookAdapter(view.getContext());
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(),3);
//        recyclerViewBook.setLayoutManager(gridLayoutManager);
//
//        recyclerViewBook.setAdapter(bookAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(),2);
        recyclerViewBook.setLayoutManager(gridLayoutManager);
        bookAdapter.setData(getListBook());
        recyclerViewBook.setAdapter(bookAdapter);
        return view;
    }

    private List<Book> getListBook() {
        List<Book> list = new ArrayList<>();
        list.add(new Book("3123","Chú Bé Rồng - Ryuuroden","djksa","https://st.nettruyengo.com/data/comics/122/ryuuroden-chu-be-rong.jpg","ddd","3213","dsadsad",2,3,1,2,3));
        list.add(new Book("23321","Tu Tiên Trở Về Tại Vườn Trường","djksa","https://st.nettruyengo.com/data/comics/203/deatte-5-byou-de-battle.jpg","https://st.nettruyengo.com/data/comics/122/ryuuroden-chu-be-rong.jpg","3213","dsadsad",2,3,1,2,3));
        list.add(new Book("23331","Khởi Tạo Nhân Vật Phản Diện","djksa","https://st.nettruyengo.com/data/comics/122/ryuuroden-chu-be-rong.jpg","ddd","3213","dsadsad",2,3,1,2,3));
        list.add(new Book("23321","Tu Chân Nói Chuyện Phiếm Quần","djksa","https://st.nettruyengo.com/data/comics/121/vu-em-la-co-tien.jpg","ddd","3213","dsadsad",2,3,1,2,3));


        return list;
    }


    public void Slider(){
        SliderAdapter sliderAdapter = new SliderAdapter(images);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();
    }
}
