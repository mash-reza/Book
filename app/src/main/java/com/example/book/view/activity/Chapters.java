package com.example.book.view.activity;

import android.app.SearchManager;
import android.app.slice.Slice;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


import com.example.book.adapter.ChapterAdapter;
import com.example.book.R;
import com.example.book.adapter.FavoriteAdapter;
import com.example.book.adapter.SearchAdapter;
import com.example.book.database.Sqlite;

import java.io.IOException;

public class Chapters extends AppCompatActivity {
    private static final String TAG = "Chapters";
    public static final String LIST_BACKGROUND = "images/list_description_background.jpg";
    ImageView imageView;

    //SearchAdapter adapter;
    //ListView listView;
    RecyclerView searchRecyclerView;
    RecyclerView recyclerView;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapters);

        //BACKGROUND
        imageView = findViewById(R.id.list_background);
        try {
            imageView.setImageDrawable(Drawable.createFromStream(this.getAssets().open(LIST_BACKGROUND), ""));
        } catch (IOException e) {
            e.printStackTrace();
        }


        //search
        searchRecyclerView = findViewById(R.id.search_recycler_view);
        LinearLayoutManager searchLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        searchRecyclerView.setLayoutManager(searchLayoutManager);


        //Recyclerview
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(linearLayoutManager);
        ChapterAdapter adapter = null;
        try {
            adapter = new ChapterAdapter(this, Sqlite.getInstance(this).getChapters());
        } catch (IOException e) {
            e.printStackTrace();
        }
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.chapter_page_menu, menu);
        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
        searchView.setIconified(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                FavoriteAdapter searchAdapter;
                try {
                    searchAdapter = new FavoriteAdapter(Chapters.this, Sqlite.getInstance(Chapters.this).getChapters(s));
                    searchRecyclerView.setAdapter(searchAdapter);
                } catch (IOException e) {
                    Log.e(TAG, "onCreate: ", e);
                }
                searchRecyclerView.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.equals("")) {
                    searchRecyclerView.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
                return false;
            }

        });
//        searchView.setOnCloseListener(() -> {
//            //listView.setAdapter(null);
//            listView.setVisibility(View.GONE);
//            Toast.makeText(this, "back pressed", Toast.LENGTH_SHORT).show();
//            return false;
//        });


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                onSearchRequested();
                return true;
            case R.id.settings:
                startActivity(new Intent(Chapters.this, Settings.class));
                return true;
            case R.id.favorites:
                startActivity(new Intent(Chapters.this, Favorites.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        searchRecyclerView.setAdapter(null);
        searchRecyclerView.setVisibility(View.GONE);
    }






}
