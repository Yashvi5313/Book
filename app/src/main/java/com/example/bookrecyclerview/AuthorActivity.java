package com.example.bookrecyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookrecyclerview.Adapter.BookItemAdapter;
import com.example.bookrecyclerview.Model.BookItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AuthorActivity extends AppCompatActivity {

    String AuthorData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authors);

        Intent intent =getIntent();
        AuthorData = intent.getStringExtra("Author");

        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<BookItem>>(){}.getType();
        List<BookItem> bookList = gson.fromJson(AuthorData , listType);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(AuthorActivity.this,  3);
        RecyclerView idRecyclerviewAuthor = (RecyclerView) findViewById(R.id.idRecyclerviewAuthor);

        BookItemAdapter bookItemAdapter = new BookItemAdapter(bookList);
        idRecyclerviewAuthor.setAdapter(bookItemAdapter);
        idRecyclerviewAuthor.setLayoutManager(gridLayoutManager);

         ImageView idAuthorImg;
         idAuthorImg = (ImageView) findViewById(R.id.idAuthorImg);
         idAuthorImg.setImageResource(bookList.get(0).getAuthorImg());

         TextView idAuthorName;
         idAuthorName = (TextView) findViewById(R.id.idAuthorName);
         idAuthorName.setText(bookList.get(0).getAuthorName());

    }
}
