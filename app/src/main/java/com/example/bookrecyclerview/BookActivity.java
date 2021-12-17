package com.example.bookrecyclerview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookrecyclerview.Model.AddToCart;
import com.example.bookrecyclerview.Model.BookItem;
import com.google.gson.Gson;

public class BookActivity extends AppCompatActivity {

    String BookData;
    ImageView imgBookImg;
    TextView BookName, AuthorName, idBookPrice;
    Button btnAddtoCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        imgBookImg = (ImageView) findViewById(R.id.imgBookImg);
        BookName = (TextView) findViewById(R.id.BookName);
        AuthorName = (TextView) findViewById(R.id.AuthorName);
        idBookPrice = (TextView)findViewById(R.id.idBookPrice);

        Intent intent = getIntent();
        BookData = intent.getStringExtra("Book");
        Gson gson = new Gson();
        BookItem bookItem = gson.fromJson(BookData, BookItem.class);


        imgBookImg.setImageResource(bookItem.getImgBookImg());
        BookName.setText(bookItem.getIdBookName());
        AuthorName.setText(bookItem.getAuthorName());
        idBookPrice.setText("" + bookItem.getIdBookPrice());



        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        myEdit.putString("BookName", bookItem.getIdBookName());
        myEdit.putString("AuthorName",bookItem.getAuthorName());
        myEdit.putInt("BookImage", bookItem.getImgBookImg());
        myEdit.putFloat("BookPrice", bookItem.getIdBookPrice());
        myEdit.apply();

        btnAddtoCart = (Button) findViewById(R.id.btnAddtoCart);
        btnAddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), AddToCart.class);
                startActivity(intent1);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        String s1 = sh.getString("BookName", "");
        String s2 = sh.getString("AuthorName", "");
        int a = sh.getInt("BookImage", 0);
        float p = sh.getFloat("BookPrice", 0);

        BookName.setText(s1);
        AuthorName.setText(s2);
        imgBookImg.setImageResource(a);
        idBookPrice.setText("" + p);
    }
}
