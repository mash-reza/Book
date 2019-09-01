package com.dushreza.festive.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.IOException;

public class Chapters extends AppCompatActivity {
    private static final String TAG = "Chapters";

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapters);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        //Repository repository = new Repository(this);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(linearLayoutManager);

        //DataBase
        Sqlite sqlite = new Sqlite(this);
        try {
            sqlite.createDatabase();
            sqlite.openDataBase();
        }catch (IOException e){
            Log.e(TAG, "onCreate: ",e );
        }
        
        ChapterAdapter adapter = new ChapterAdapter(this, sqlite.getChapters());
        recyclerView.setAdapter(adapter);
    }
}
