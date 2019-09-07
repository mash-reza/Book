package com.example.book.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.example.book.R;
import com.example.book.database.Sqlite;

import java.io.IOException;

public class Splash extends AppCompatActivity {
    private static final String TAG = "Splash";
    private final String BACKGROUND_IMAGE_NAME = "images/background.jpg";

    public static final String LAUNCH_PREFERENCE = "launch_preference";
    public static final String FIRST_LAUNCH = "first_launch";


    Button button;
    int WRITE_REQUEST_CODE = 200;
    ConstraintLayout layout;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        setTheme(R.style.AppTheme);
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        button = findViewById(R.id.button2);
//        button.setOnClickListener(v -> {
//            this.startActivity(new Intent(this, Chapters.class));
//        });


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageViewSplash);


        layout = findViewById(R.id.main_layout);
        try {
            imageView.setImageDrawable(Drawable.createFromStream(this.getAssets().open(BACKGROUND_IMAGE_NAME), ""));
        } catch (IOException e) {
            Log.e(TAG, "onCreate: ", e);
        }

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(Splash.this, Chapters.class);
            startActivity(intent);
            finish();
        }, 1500);

        SharedPreferences preferences = getApplicationContext().getSharedPreferences(LAUNCH_PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Log.i(TAG, "onCreate first launch: " + preferences.getBoolean(FIRST_LAUNCH, true));
        if (preferences.getBoolean(FIRST_LAUNCH, true)) {
            try {
                Sqlite.getInstance(this).createDatabase();
            } catch (IOException e) {
                Log.e(TAG, "onCreate: ", e);
            }
            editor.putBoolean(FIRST_LAUNCH, false);
            editor.commit();
        }
        Log.i(TAG, "onCreate first launch: " + preferences.getBoolean(FIRST_LAUNCH, true));
    }
}
