package com.example.book.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.book.R;
import com.example.book.adapter.ChapterAdapter;
import com.example.book.database.Sqlite;

import java.io.IOException;

public class Favorites extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        recyclerView = findViewById(R.id.favorites_recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        ChapterAdapter adapter = null;
        try {
            adapter = new ChapterAdapter(this, Sqlite.getInstance(this).getFavoriteChapters());
        } catch (IOException e) {
            e.printStackTrace();
        }
        recyclerView.setAdapter(adapter);
    }
}
