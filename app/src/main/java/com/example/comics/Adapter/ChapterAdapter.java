package com.example.comics.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comics.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder>   {
    Context mContext;
    List<String> listImages =new ArrayList<>();
    public ChapterAdapter(Context context){
        this.mContext = context;


    }
    public void SetData(List<String> images){
        this.listImages = images;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChapterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.chapter_image_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterViewHolder holder, int position) {
        Picasso.get().load(listImages.get(position)).into(holder.chapterImages);
    }

    @Override
    public int getItemCount() {
        return listImages.size();
    }

    public class ChapterViewHolder extends RecyclerView.ViewHolder {
        ImageView chapterImages;
        public ChapterViewHolder(@NonNull View itemView) {
            super(itemView);
            chapterImages=  itemView.findViewById(R.id.chapterImages);


        }
    }

}
