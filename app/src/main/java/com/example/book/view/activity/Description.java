package com.example.book.view.activity;

import android.support.v7.preference.SeekBarPreference;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.book.R;

import java.io.IOException;
import java.io.InputStream;

public class Description extends AppCompatActivity {

    private static final String TAG = "Description";
    public static final String DISCRIPTION_BACKGROUND = "images/list_description_background.jpg";


    TextView content;
    ImageView image;
    ImageView imageView;
    SeekBarPreference seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        ActionBar actionBar = getSupportActionBar();


        imageView = findViewById(R.id.description_background);
        try {
            imageView.setImageDrawable(Drawable.createFromStream(this.getAssets().open(DISCRIPTION_BACKGROUND), ""));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Intent intent = getIntent();
        actionBar.setTitle(intent.getStringExtra("title"));


        content = findViewById(R.id.description_text);
        image = findViewById(R.id.description_image);
        content.setText(intent.getStringExtra("content"));

        InputStream in = null;
        try {
            in = this.getAssets().open("images/" + intent.getStringExtra("image"));
            Log.i(TAG, "onCreate: " + intent.getStringExtra("image"));
//            in = this.getAssets().open("images/01.jpg");
        } catch (IOException e) {
            System.err.println(e);
        }
        Drawable drawable = Drawable.createFromStream(in, "image");

        Glide.with(this).load(drawable).into(image);

        PreferenceManager.setDefaultValues(this, R.xml.prefrences, false);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        float size = Float.parseFloat(preferences.getString("key_text_size", "18"));
        Log.i(TAG, "onCreate: " + preferences.getString("key_text_size", "18"));
        Log.i(TAG, "onCreate: " + size);
        String font = preferences.getString("key_text_font", "comic.ttf");
        content.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/" + font));
        content.setTextSize(size);
    }
}
