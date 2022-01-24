package com.example.comics.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comics.Models.books;
import com.example.comics.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private Context mContext;
    private List<books> mListBook;
    public BookAdapter(Context mContext){
        this.mContext = mContext;
    }
    public void setData(List<books> list){
        this.mListBook =list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_gridlayout_3,parent,false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        books book = mListBook.get(position);
        if(book==null){
            return;
        }
        Picasso.get().load(mListBook.get(position).getThumb()).into(holder.imgBook);
        holder.tvTittle.setText(book.getTitle());
    }

    @Override
    public int getItemCount() {
        if(mListBook !=null) {
            return mListBook.size();
        }
        return 0;
    }

    public class BookViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgBook;
        private TextView tvTittle;
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBook = itemView.findViewById(R.id.book_img);
            tvTittle = itemView.findViewById(R.id.book_tittle);
        }
    }
}
