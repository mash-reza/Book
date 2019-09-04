package com.example.book.view.activity;

import android.os.Bundle;

import android.preference.PreferenceFragment;
import android.preference.*;
import android.support.v7.app.AppCompatActivity;

import com.example.book.R;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //i used fragmentManager instead of getSupportFragmentManager
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MainPreferenceFragment()).commit();
    }

    public static class MainPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.prefrences);
            bindPreferenceValueToSummary(findPreference("key_text_size"));
        }
    }

    private static void bindPreferenceValueToSummary(Preference preference) {
        preference.setOnPreferenceChangeListener((preference1, newValue) -> {
            if (preference1 instanceof ListPreference) {
                int index = ((ListPreference) preference1).findIndexOfValue(newValue.toString());
                ListPreference listPreference = (ListPreference) preference1;
                listPreference.setSummary(
                        index >= 0 ? ((ListPreference) preference1).getEntries()[index] : null
                );
            }
            return true;
        });
    }
}
