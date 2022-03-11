package com.example.comics.Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comics.Activity.ChapterActivity;
import com.example.comics.Models.Book;
import com.example.comics.R;

public class BottomSheetAdapter extends RecyclerView.Adapter<BottomSheetAdapter.ViewHolder> {
    Book book;
    Context mContext;

    public BottomSheetAdapter(Book book, Context mContext) {
        this.book = book;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_bottomsheet,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTittle.setText(book.getChapters().get(position).getTitle());
        holder.tvTime.setText(book.getChapters().get(position).getTime().split(" ")[0]);
    }

    @Override
    public int getItemCount() {
        return book.getChapters().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTittle,tvTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTittle = itemView.findViewById(R.id.tvTittleBottomSheet);
            tvTime   = itemView.findViewById(R.id.tvTime);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, ChapterActivity.class);
                    intent.putExtra("position",getAdapterPosition());
                    intent.putExtra("book",book);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
