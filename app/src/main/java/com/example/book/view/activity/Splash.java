package com.example.book.view.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.example.book.R;

import java.io.IOException;

public class Splash extends AppCompatActivity {
    private static final String TAG = "Splash";
    private final String BACKGROUND_IMAGE_NAME = "images/background.jpg";

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
            imageView.setImageDrawable(Drawable.createFromStream(this.getAssets().open(BACKGROUND_IMAGE_NAME),""));
        } catch (IOException e) {
            Log.e(TAG, "onCreate: ", e);
        }

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(Splash.this, Chapters.class);
            startActivity(intent);
            finish();
        }, 1500);
    }
}
