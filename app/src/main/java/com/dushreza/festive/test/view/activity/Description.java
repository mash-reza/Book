package com.dushreza.festive.test;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;

public class Description extends AppCompatActivity {

    private static final String TAG = "Description";

    TextView title;
    TextView content;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        Intent intent = getIntent();
        title = findViewById(R.id.description_title);
        content = findViewById(R.id.description_text);
        image = findViewById(R.id.description_image);
        title.setText(intent.getStringExtra("title"));
        content.setText(intent.getStringExtra("content"));

        InputStream in = null;
        try {
            in = this.getAssets().open("images/" + intent.getStringExtra("image"));
            Log.i(TAG, "onCreate: "+intent.getStringExtra("image"));
//            in = this.getAssets().open("images/01.jpg");
        } catch (IOException e) {
            System.err.println(e);
        }
        Drawable drawable = Drawable.createFromStream(in,"image");

        Glide.with(this).load(drawable).into(image);
    }

}
