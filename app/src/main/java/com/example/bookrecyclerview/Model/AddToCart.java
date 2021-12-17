package com.example.bookrecyclerview.Model;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookrecyclerview.Adapter.AddToCartAdapter;
import com.example.bookrecyclerview.R;

import java.util.ArrayList;
import java.util.List;

public class AddToCart extends AppCompatActivity {

    String Book, Author;
    int bookImg;
    int bookQuantity;
    String BookQuantity;
    Float bookPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        Book =  sharedPreferences.getString("BookName", null);
        Author = sharedPreferences.getString("AuthorName", null);
        bookImg = sharedPreferences.getInt("BookImage", 0);
        bookPrice = sharedPreferences.getFloat("BookPrice", 0);

        CartItem cartItem = new CartItem(bookImg, Book, Author, 1, bookPrice);
        List<CartItem> cartItemList = new ArrayList<>();
        cartItemList.add(cartItem);


        LinearLayoutManager layoutManager = new LinearLayoutManager(AddToCart.this);
        RecyclerView idAddRecycler = (RecyclerView) findViewById(R.id.idAddRecycler);
        AddToCartAdapter addToCartAdapter = new AddToCartAdapter(cartItemList);

        idAddRecycler.setAdapter(addToCartAdapter);
        idAddRecycler.setLayoutManager(layoutManager);

        TextView idTotalPrice = findViewById(R.id.idTotalPrice);
        idTotalPrice.setText("" + bookPrice);

        TextView idTotalQuantity = findViewById(R.id.idTotalQuantity);
        addToCartAdapter.setOnSetAddClickListener(new AddToCartAdapter.BookAddClickEvent() {
            @Override
            public void onAddClick(CartItem cartItem, int pos) {

                bookQuantity = bookQuantity + 1;
                idTotalQuantity.setText("" + bookQuantity);

                bookPrice = bookPrice + (cartItem.getPrice());
                idTotalPrice.setText("" + bookPrice);
            }
        });

        addToCartAdapter.setOnSetMinusClickListener(new AddToCartAdapter.BookMinusClickEvent() {
            @Override
            public void onMinusClick(CartItem cartItem, int pos) {

                if (bookQuantity >= 1) {
                    bookQuantity -= 1;
                }
                idTotalQuantity.setText("" + bookQuantity);

                if (bookPrice >= 1) {
                    bookPrice = bookPrice - (cartItem.getPrice());
                    idTotalPrice.setText("" + bookPrice);
                }
            }
        });
    }
}
