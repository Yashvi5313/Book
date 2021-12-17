package com.example.bookrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookrecyclerview.Adapter.AuthorItemAdapter;
import com.example.bookrecyclerview.Adapter.BookItemAdapter;
import com.example.bookrecyclerview.Model.AuthorItem;
import com.example.bookrecyclerview.Model.BookItem;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<BookItem> bookItemList;
    ArrayList<AuthorItem> authorItemList;
    String Author = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);

        RecyclerView idRecyclerview = findViewById(R.id.idRecyclerview);
        BookItemAdapter bookItemAdapter = new BookItemAdapter(trendingBookItemList());
        idRecyclerview.setAdapter(bookItemAdapter);
        idRecyclerview.setLayoutManager(layoutManager);


        RecyclerView idRecyclerview2 = findViewById(R.id.idRecyclerview2);
        AuthorItemAdapter authorItemAdapter = new AuthorItemAdapter(bookAuthorItemList());
        idRecyclerview2.setAdapter(authorItemAdapter);
        idRecyclerview2.setLayoutManager(layoutManager1);


        TextView idSeeAll = (TextView) findViewById(R.id.idSeeAll);
        idSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (idSeeAll.getText() == "See All") {
                    idSeeAll.setText("See Less");
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 3);
                    idRecyclerview.setLayoutManager(gridLayoutManager);
                } else {
                    idSeeAll.setText("See All");
                    LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                    idRecyclerview.setLayoutManager(layoutManager);
                }
            }
        });

        RelativeLayout idRelative = (RelativeLayout) findViewById(R.id.idRelative);
        idRelative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<BookItem> list = filterList("Motivation");
                Gson gson = new Gson();
                String BookJson = gson.toJson(list);
                Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
                intent.putExtra("Key", BookJson);
                startActivity(intent);
                //bookItemAdapter.Update(list);
            }
        });

        RelativeLayout idRelative1 = (RelativeLayout) findViewById(R.id.idRelative1);
        idRelative1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<BookItem> list = filterList("Science");
                Gson gson = new Gson();
                String BookJson = gson.toJson(list);
                Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
                intent.putExtra("Key", BookJson);
                startActivity(intent);
                //bookItemAdapter.Update(list);
            }
        });

        RelativeLayout idRelative2 = (RelativeLayout) findViewById(R.id.idRelative2);
        idRelative2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<BookItem> list = filterList("Health");
                Gson gson = new Gson();
                String BookJson = gson.toJson(list);
                Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
                intent.putExtra("Key", BookJson);
                startActivity(intent);
                //bookItemAdapter.Update(list);
            }
        });

        RelativeLayout idRelative3 = (RelativeLayout) findViewById(R.id.idRelative3);
        idRelative3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<BookItem> list = filterList("Education");
                Gson gson = new Gson();
                String BookJson = gson.toJson(list);
                Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
                intent.putExtra("Key", BookJson);
                startActivity(intent);
                //bookItemAdapter.Update(list);
            }
        });

        RelativeLayout idRelative4 = (RelativeLayout) findViewById(R.id.idRelative4);
        idRelative4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<BookItem> list = filterList("Psychology");
                Gson gson = new Gson();
                String BookJson = gson.toJson(list);
                Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
                intent.putExtra("Key", BookJson);
                startActivity(intent);
                //bookItemAdapter.Update(list);
            }
        });

        RelativeLayout idRelative5 = (RelativeLayout) findViewById(R.id.idRelative5);
        idRelative5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<BookItem> list = filterList("Investments");
                Gson gson = new Gson();
                String BookJson = gson.toJson(list);
                Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
                intent.putExtra("Key", BookJson);
                startActivity(intent);
                // bookItemAdapter.Update(list);
            }
        });

        authorItemAdapter.setOnAuthorClickListner(new AuthorItemAdapter.AuthorClickEvent() {
            @Override
            public void onAuthorClick(AuthorItem authorItem, int pos) {
                ArrayList<BookItem> authorList = new ArrayList<>();
                for (int i = 0; i < trendingBookItemList().size(); i++) {
                    if (bookItemList.get(i).getAuthorName().equals(authorItem.getAuthorName())) {
                        authorList.add(bookItemList.get(i));
                    }
                }
                if (authorList.size() <= 0) {
                    Toast.makeText(getApplicationContext(), "Book Not Found!!!", Toast.LENGTH_LONG).show();
                }
                //authorItemAdapter.Update(authorList);
                Gson gson = new Gson();
                String AuthorJson = gson.toJson(authorList);
                Intent intent = new Intent(MainActivity.this, AuthorActivity.class);
                intent.putExtra("Author", AuthorJson);
                startActivity(intent);
            }
        });

        bookItemAdapter.setOnBookClickListner(new BookItemAdapter.BookClickEvent() {
            @Override
            public void onBookClick(BookItem bookItem, int pos) {
                Gson gson = new Gson();
                String BookJson = gson.toJson(bookItem);
                Intent intent = new Intent(MainActivity.this, BookActivity.class);
                intent.putExtra("Book", BookJson);
                startActivity(intent);
            }
        });
    }

    public ArrayList<BookItem> filterList(String type) {
        ArrayList<BookItem> list = new ArrayList<>();

        for (int i = 0; i < bookItemList.size(); i++) {
            if (bookItemList.get(i).getCategory().equals(type)) {
                list.add(bookItemList.get(i));
            }
        }
        if (list.size() <= 0) {
            Toast.makeText(getApplicationContext(), "Book Not Found!!!", Toast.LENGTH_LONG).show();
        }
        return list;
    }


    private List<BookItem> trendingBookItemList() {
        bookItemList = new ArrayList<>();
        bookItemList.add(new BookItem(R.drawable.beach_town, "Beach Town", "Mary Kay Andrews", R.drawable.mary_kay_andrews, "Motivation", 6));
        bookItemList.add(new BookItem(R.drawable.loss, "Loss Of Reason", "Miles A. Maxwell", R.drawable.miles_a_maxwell, "Education", 5.25f));
        bookItemList.add(new BookItem(R.drawable.first_strike, "First Strike", "Ben Coes", R.drawable.ben_coes, "Science", 1));
        bookItemList.add(new BookItem(R.drawable.independence_day, "Independence Day", "Ben Coes", R.drawable.ben_coes, "Science", 9));
        bookItemList.add(new BookItem(R.drawable.army_of_none, "Army Of None", "Paul Scharre", R.drawable.paul_scharre, "Education", 8.95f));
        bookItemList.add(new BookItem(R.drawable.drone, "Drone: A Short Story Thriller", "Miles A. Maxwell", R.drawable.miles_a_maxwell, "Education", 5.51f));
        bookItemList.add(new BookItem(R.drawable.power_down, "Power Down", "Ben Coes", R.drawable.ben_coes, "Science", 9));
        bookItemList.add(new BookItem(R.drawable.pull_of_star, "The Pull Of The Stars", "Emma Donoghue", R.drawable.emma_donoghue, "Science", 5.51f));
        bookItemList.add(new BookItem(R.drawable.room, "Room (novel)", "Emma Donoghue", R.drawable.emma_donoghue, "Science", 0));
        bookItemList.add(new BookItem(R.drawable.hello_summer, "Hello Summer", "Mary Kay Andrews", R.drawable.mary_kay_andrews, "Health", 3.54f));
        bookItemList.add(new BookItem(R.drawable.finding_reason, "Finding Reason", "Miles A. Maxwell", R.drawable.miles_a_maxwell, "Education", 2.36f));
        bookItemList.add(new BookItem(R.drawable.makers_of_india, "Makers of Modern India", "Ramachandra Guha", R.drawable.ramachandra_guha, "Motivation", 6.11f));
        bookItemList.add(new BookItem(R.drawable.gandhi_before_india, "Gandhi Before India", "Ramachandra Guha", R.drawable.ramachandra_guha, "Investments", 3.53f));
        bookItemList.add(new BookItem(R.drawable.prospects_of_women, "Prospects Of A Women", "Wendy Voorsanger's", R.drawable.wendy_voorsanger, "Psychology", 9));
        bookItemList.add(new BookItem(R.drawable.remember_me, "Remember Me", "Christopher Pike", R.drawable.christopher_pike, "Health", 7));
        bookItemList.add(new BookItem(R.drawable.german_midwife, "The German Midwife", "Mandy Robotham", R.drawable.mandy_robotham, "Psychology", 8.92f));
        bookItemList.add(new BookItem(R.drawable.india_after_gandhi, "India After Gandhi", "Ramachandra Guha", R.drawable.ramachandra_guha, "Motivation", 1.18f));
        bookItemList.add(new BookItem(R.drawable.inseparable, "Inseparable: Desire Between Women in Literature", "Emma Donoghue", R.drawable.emma_donoghue, "Science", 5.25f));
        bookItemList.add(new BookItem(R.drawable.the_weekenders, "The Weekenders", "Mary Kay Andrews", R.drawable.mary_kay_andrews, "Motivation", 6));
        bookItemList.add(new BookItem(R.drawable.sunset_beach, "Sunset Beach", "Mary Kay Andrews", R.drawable.mary_kay_andrews, "Motivation", 10));
        bookItemList.add(new BookItem(R.drawable.environmentalism, "Environmentalism: A Global History", "Ramachandra Guha", R.drawable.ramachandra_guha, "Investments", 1.92f));
        bookItemList.add(new BookItem(R.drawable.the_secret, "The Secret Messenger", "Mandy Robotham", R.drawable.mandy_robotham, "Psychology", 2.3f));
        bookItemList.add(new BookItem(R.drawable.the_unique_wood, "The Unquiet Woods", "Ramachandra Guha", R.drawable.ramachandra_guha, "Motivation", 0));
        bookItemList.add(new BookItem(R.drawable.berlin_girl, "The Berlin Girl", "Mandy Robotham", R.drawable.mandy_robotham, "Psychology", 3.53f));

        return bookItemList;
    }

    private List<AuthorItem> bookAuthorItemList() {
        ArrayList<AuthorItem> authorItemList = new ArrayList<>();
        authorItemList.add(new AuthorItem(R.drawable.mary_kay_andrews, "Mary Kay Andrews"));
        authorItemList.add(new AuthorItem(R.drawable.miles_a_maxwell, "Miles A. Maxwell"));
        authorItemList.add(new AuthorItem(R.drawable.ben_coes, "Ben Coes"));
        authorItemList.add(new AuthorItem(R.drawable.paul_scharre, "Paul Scharre"));
        authorItemList.add(new AuthorItem(R.drawable.ramachandra_guha, "Ramachandra Guha"));
        authorItemList.add(new AuthorItem(R.drawable.wendy_voorsanger, "Wendy Voorsanger's"));
        authorItemList.add(new AuthorItem(R.drawable.emma_donoghue, "Emma Donoghue"));
        authorItemList.add(new AuthorItem(R.drawable.christopher_pike, "Christopher Pike"));
        authorItemList.add(new AuthorItem(R.drawable.mandy_robotham, "Mandy Robotham"));

        return authorItemList;
    }
}