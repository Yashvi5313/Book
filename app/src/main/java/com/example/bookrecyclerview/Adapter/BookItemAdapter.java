package com.example.bookrecyclerview.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookrecyclerview.Model.BookItem;
import com.example.bookrecyclerview.R;

import java.util.List;

public class BookItemAdapter extends RecyclerView.Adapter<BookItemAdapter.ViewHolder> {

    private List<BookItem> bookItemList;
    BookClickEvent bookClickEvent;

    public BookItemAdapter(List<BookItem> bookItemList) {
        this.bookItemList = bookItemList;
    }

    public void Update(List<BookItem> bookItemList) {
      this.bookItemList = bookItemList;
      notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View bookcard = layoutInflater.inflate(R.layout.book_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(bookcard);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        BookItem bookItems = bookItemList.get(position);
        holder.imageView.setImageResource(bookItemList.get(position).getImgBookImg());
        holder.textView.setText(bookItemList.get(position).getIdBookName());
        holder.bookCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookClickEvent.onBookClick(bookItems, position);
            }
        });
    }

    public void setOnBookClickListner(BookClickEvent bookClickEvent) {
        this.bookClickEvent = bookClickEvent;
    }

    public interface BookClickEvent {
        void onBookClick(BookItem bookItem, int pos);
    }

    @Override
    public int getItemCount() {
        return bookItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public LinearLayout linearLayout;
        public CardView bookCard;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imgBookImg);
            this.textView = (TextView) itemView.findViewById(R.id.idBookName);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
            this.bookCard = (CardView) itemView.findViewById(R.id.bookCard);
        }
    }
}
