package com.example.book.view.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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


import com.example.book.Repository;
import com.example.book.adapter.ChapterAdapter;
import com.example.book.R;
import com.example.book.adapter.SearchAdapter;
import com.example.book.database.Sqlite;

import java.io.IOException;

public class Chapters extends AppCompatActivity {
    private static final String TAG = "Chapters";
    public static final String LIST_BACKGROUND = "images/list_description_background.jpg";
    ImageView imageView;

    //SearchAdapter adapter;
    ListView listView;
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
        listView = findViewById(R.id.listview);
        //searchView = findViewById(R.id.search);
        //searchView.setQueryHint("Enter Search");

        handleIntent(getIntent());

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
                try {
                    listView.setAdapter(new SearchAdapter(Chapters.this, Sqlite.getInstance(Chapters.this).getChapters(s)));
                    listView.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.equals("")) {
                    listView.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
                return false;
            }

        });
        searchView.setOnCloseListener(() -> {
            //listView.setAdapter(null);
            listView.setVisibility(View.GONE);
            Toast.makeText(this, "back pressed", Toast.LENGTH_SHORT).show();
            return false;
        });


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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        listView.setAdapter(null);
        listView.setVisibility(View.GONE);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void search(String query) throws IOException {
//        SearchAdapter adapter = new SearchAdapter(this, Sqlite.getInstance(this).getChapters(query));
        Log.i(TAG, "search: " + "method ran");
        SearchAdapter adapter = new SearchAdapter(this, Repository.getChapters());
        listView.setAdapter(adapter);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            try {
                search(query);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
