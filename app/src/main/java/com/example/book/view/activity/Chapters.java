package com.example.book.view.activity;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.book.adapter.ChapterAdapter;
import com.dushreza.festive.test.R;
import com.example.book.database.Sqlite;

import java.io.IOException;

public class Chapters extends AppCompatActivity {
    private static final String TAG = "Chapters";
    public static final String LIST_BACKGROUND = "images/list_description_background.jpg";
    ImageView imageView;

    EditText search;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapters);

        imageView = findViewById(R.id.list_background);
        try {
            imageView.setImageDrawable(Drawable.createFromStream(this.getAssets().open(LIST_BACKGROUND),""));
        } catch (IOException e) {
            e.printStackTrace();
        }


        search = findViewById(R.id.search_edit_text);
        search.setOnEditorActionListener((v, actionId, event) -> {
            if(actionId == EditorInfo.IME_ACTION_SEARCH){

            }
            return false;
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        //Repository repository = new Repository(this);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(linearLayoutManager);

        //DataBase
//        Sqlite sqlite = new Sqlite(this);
//        try {
//            sqlite.createDatabase();
//            sqlite.openDataBase();
//        }catch (IOException e){
//            Log.e(TAG, "onCreate: ",e );
//        }

        ChapterAdapter adapter = null;
        try {
            adapter = new ChapterAdapter(this, Sqlite.getInstance(this).getChapters());
        } catch (IOException e) {
            e.printStackTrace();
        }
        recyclerView.setAdapter(adapter);
    }
}
