package com.example.bookrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.bookrecyclerview.Adapter.BookItemAdapter;
import com.example.bookrecyclerview.Model.BookItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CategoriesActivity extends AppCompatActivity {

    String BookData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        Intent intent =getIntent();
        BookData = intent.getStringExtra("Key");
        //textView.setText(String.valueOf(BookData));

        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<BookItem>>(){}.getType();
        List<BookItem> authorList = gson.fromJson(BookData , listType);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(CategoriesActivity.this,  3);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.idRecycler);

        BookItemAdapter bookItemAdapter = new BookItemAdapter(authorList);
        recyclerView.setAdapter(bookItemAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);
    }
}

