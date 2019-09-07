package com.example.book.view.activity;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.example.book.R;
import com.example.book.adapter.ChapterAdapter;
import com.example.book.adapter.FavoriteAdapter;
import com.example.book.database.Sqlite;

import java.io.IOException;

public class Favorites extends AppCompatActivity {

    private static final String TAG = "Favorites";

    ImageView background;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        background = findViewById(R.id.favorites_list_background);
        try {
            background.setImageDrawable(Drawable.createFromStream(this.getAssets().open(Chapters.LIST_BACKGROUND), ""));
        } catch (IOException e) {
            Log.e(TAG, "onCreate: ", e);
        }


        recyclerView = findViewById(R.id.favorites_recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        FavoriteAdapter adapter = null;
        try {
            adapter = new FavoriteAdapter(this, Sqlite.getInstance(this).getFavoriteChapters());
        } catch (IOException e) {
            e.printStackTrace();
        }
        recyclerView.setAdapter(adapter);
    }
}
