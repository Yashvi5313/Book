package com.example.bookrecyclerview.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookrecyclerview.Model.CartItem;
import com.example.bookrecyclerview.R;

import java.util.List;

public class AddToCartAdapter extends RecyclerView.Adapter<AddToCartAdapter.ViewHolder> {

    private List<CartItem> cartItemList;
    public BookAddClickEvent bookAddClickEvent;
    public BookMinusClickEvent bookMinusClickEvent;

    public AddToCartAdapter(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View addcard = layoutInflater.inflate(R.layout.add_card, parent, false);
        AddToCartAdapter.ViewHolder viewHolder = new AddToCartAdapter.ViewHolder(addcard);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final CartItem cartItem = cartItemList.get(position);
        holder.imgBookImg1.setImageResource(cartItemList.get(position).getBookImage());
        holder.BookName1.setText(cartItemList.get(position).getBookName());
        holder.AuthorName1.setText(cartItemList.get(position).getAuthorName());
        holder.idBookQuntity.setText("" + cartItemList.get(position).getQuantity());
        holder.bookPrice.setText("" + cartItemList.get(position).getPrice());
        holder.idImgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartItem.addToCart();
                holder.idBookQuntity.setText("" + cartItem.getQuantity());
                notifyDataSetChanged();
                bookAddClickEvent.onAddClick(cartItem, position);
            }
        });

        holder.idImgMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartItem.removeFromCart();
                holder.idBookQuntity.setText("" + cartItem.getQuantity());
                notifyDataSetChanged();
                bookMinusClickEvent.onMinusClick(cartItem, position);
            }
        });
    }

    public void setOnSetAddClickListener(BookAddClickEvent bookAddClickEvent){
        this.bookAddClickEvent = bookAddClickEvent;
    }


    public interface BookAddClickEvent {
        void onAddClick(CartItem cartItem, int pos);
    }

    public void setOnSetMinusClickListener(BookMinusClickEvent bookMinusClickEvent){
        this.bookMinusClickEvent = bookMinusClickEvent;
    }


    public interface BookMinusClickEvent {
        void onMinusClick(CartItem cartItem, int pos);
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgBookImg1;
        public TextView BookName1;
        public TextView AuthorName1;
        public TextView idBookQuntity;
        public TextView bookPrice;
        public ImageView idImgAdd;
        public ImageView idImgMinus;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView){
            super(itemView);
            this.imgBookImg1 = (ImageView) itemView.findViewById(R.id.imgBookImg1);
            this.BookName1 = (TextView) itemView.findViewById(R.id.BookName1);
            this.AuthorName1 = (TextView) itemView.findViewById(R.id.AuthorName1);
            this.idBookQuntity = (TextView) itemView.findViewById(R.id.idBookQuntity);
            this.bookPrice = (TextView) itemView.findViewById(R.id.bookPrice);
            this.idImgAdd = (ImageView) itemView.findViewById(R.id.idImgAdd);
            this.idImgMinus = (ImageView) itemView.findViewById(R.id.idImgMinus);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.addLinear);
        }
    }
}
