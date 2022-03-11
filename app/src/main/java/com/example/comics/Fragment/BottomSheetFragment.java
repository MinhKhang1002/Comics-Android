package com.example.comics.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.comics.Adapter.BottomSheetAdapter;
import com.example.comics.Models.Book;
import com.example.comics.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetFragment extends BottomSheetDialogFragment {
    View view;
    Context context;

    RecyclerView recyclerView;
    Book book = new Book();
    BottomSheetAdapter bottomSheetAdapter;
    public BottomSheetFragment(Context context, Book book){
        this.context = context;
        this.book=book;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view= inflater.inflate(R.layout.fragment_bottom_sheet, container, false);
        recyclerView = view.findViewById(R.id.recycleViewChapterList);

         return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog= (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View viewDialog = LayoutInflater.from(getContext()).inflate(R.layout.fragment_bottom_sheet,null);
        bottomSheetDialog.setContentView(viewDialog);
        bottomSheetAdapter = new BottomSheetAdapter(book, getContext());
        return bottomSheetDialog;
    }

}