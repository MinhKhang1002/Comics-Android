package com.example.comics.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.comics.Adapter.BottomSheetAdapter;
import com.example.comics.Models.Book;
import com.example.comics.Models.Chapter;
import com.example.comics.Models.Response;
import com.example.comics.R;
import com.example.comics.Retrofit.RetrofitClient;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class BottomSheetFragment extends BottomSheetDialogFragment {
    View view;
    Context context;
    Response<Chapter> reponseChapter = new Response<>();
    List<Chapter> listChapter = new ArrayList<>();
    RecyclerView recyclerView;
    Book book = new Book();
    BottomSheetAdapter bottomSheetAdapter;
    public BottomSheetFragment(Context context){
        this.context = context;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view= inflater.inflate(R.layout.fragment_bottom_sheet, container, false);
        recyclerView = view.findViewById(R.id.recycleViewChapterList);
        book.setEndpoint(requireActivity().getIntent().getStringExtra("endpoint")) ;
        getListChapter();

         return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog= (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View viewDialog = LayoutInflater.from(getContext()).inflate(R.layout.fragment_bottom_sheet,null);
        bottomSheetDialog.setContentView(viewDialog);
        bottomSheetAdapter = new BottomSheetAdapter(getContext());
        return bottomSheetDialog;
    }

    public void getListChapter(){
        RetrofitClient.getAPI().getListChapter(book.getEndpoint()).enqueue(new Callback<Response<Chapter>>() {
            @Override
            public void onResponse(Call<Response<Chapter>> call, retrofit2.Response<Response<Chapter>> response) {
                reponseChapter = response.body();
                listChapter = reponseChapter.getData();
                bottomSheetAdapter.setData(listChapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);

                recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(),LinearLayoutManager.VERTICAL));
                recyclerView.setAdapter(bottomSheetAdapter);

            }

            @Override
            public void onFailure(Call<Response<Chapter>> call, Throwable t) {
                Toast.makeText(view.getContext(), "SAIIIIIIIIIIIIII", Toast.LENGTH_SHORT).show();
            }
        });
    }

}