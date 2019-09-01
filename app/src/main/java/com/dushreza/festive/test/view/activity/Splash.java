package com.dushreza.festive.test.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.dushreza.festive.test.R;
import com.dushreza.festive.test.database.Sqlite;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button button;
    int WRITE_REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Sqlite sqlite = new Sqlite(this);
        try {
            sqlite.createDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlite.openDataBase();

        //sqlite.Read();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button2);
        button.setOnClickListener(v -> {
            this.startActivity(new Intent(this, Chapters.class));
        });
    }
}
