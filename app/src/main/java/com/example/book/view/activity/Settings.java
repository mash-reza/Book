package com.example.book.view.activity;


import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;

import com.example.book.R;

import java.io.IOException;

public class Settings extends AppCompatActivity {
    private static final String TAG = "Settings";
    public static final String SETTINGS_PREFERENCES = "settings_pref";
    public static final String TEXT_PREF_KEY = "text_size";
    public static final String FONT_PREF_KEY = "text_font";

    public static final String FONT_01 = "comic.ttf";
    public static final String FONT_02 = "consolas.ttf";
    public static final String FONT_03 = "impact.ttf";

    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    ImageView imageView;
    SeekBar seekBar;
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView = findViewById(R.id.settings_background_image_view);
        seekBar = findViewById(R.id.text_size_seekBar);
        radioButton1 = findViewById(R.id.first_font_radioButton);
        radioButton2 = findViewById(R.id.second_font_radioButton);
        radioButton3 = findViewById(R.id.third_font_radioButton);


        setBackground();
        setSettingsPreferences();
        setSeekBar();
        setRadioButtons();

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return false;
    }

    private void setBackground() {
        try {
            imageView.setImageDrawable(Drawable.createFromStream(this.getAssets().open(Chapters.LIST_BACKGROUND), ""));
        } catch (IOException e) {
            Log.e(TAG, "setBackground: ", e);
        }
    }

    private void setSettingsPreferences() {
        preferences = getSharedPreferences(SETTINGS_PREFERENCES, MODE_PRIVATE);
        editor = preferences.edit();
    }


    private void setRadioButtons() {
        String defaultFont = preferences.getString(FONT_PREF_KEY, "comic.ttf");
        switch (defaultFont) {
            case FONT_01:
                radioButton1.setChecked(true);
                break;
            case FONT_02:
                radioButton2.setChecked(true);
                break;
            case FONT_03:
                radioButton3.setChecked(true);
                break;
        }
    }

    public void onRadioButtonClicked(View view) {
        switch (view.getId()) {
            case R.id.first_font_radioButton:
                editor.putString(FONT_PREF_KEY, "comic.ttf");
                editor.commit();
                break;
            case R.id.second_font_radioButton:
                editor.putString(FONT_PREF_KEY, "consolas.ttf");
                editor.commit();
                break;
            case R.id.third_font_radioButton:
                editor.putString(FONT_PREF_KEY, "impact.ttf");
                editor.commit();
                break;

        }
    }

    private void setSeekBar() {
        int defaultSize = preferences.getInt(TEXT_PREF_KEY, 24);
        switch (defaultSize) {
            case 14:
                seekBar.setProgress(0);
                break;
            case 18:
                seekBar.setProgress(1);
                break;
            case 24:
                seekBar.setProgress(2);
                break;
            case 36:
                seekBar.setProgress(3);
                break;
            case 48:
                seekBar.setProgress(4);
                break;
        }

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch (progress) {
                    case 0:
                        editor.putInt(TEXT_PREF_KEY, 14);
                        editor.commit();
                        break;
                    case 1:
                        editor.putInt(TEXT_PREF_KEY, 18);
                        editor.commit();
                        break;
                    case 2:
                        editor.putInt(TEXT_PREF_KEY, 24);
                        editor.commit();
                        break;
                    case 3:
                        editor.putInt(TEXT_PREF_KEY, 36);
                        editor.commit();
                        break;
                    case 4:
                        editor.putInt(TEXT_PREF_KEY, 48);
                        editor.commit();
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

}
