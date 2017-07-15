package com.somecompany.homeaway;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.gson.Gson;

import java.io.File;

public class PreferencesExplorer extends AppCompatActivity {
    private File myPreferencesFile;
    private Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences_explorer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void loadPreferences() {
        File openPreferencesFile = new File("savedPreferences.txt");
        this.myPreferencesFile = openPreferencesFile;
        Gson gson = new Gson();
        preferences = gson.fromJson(myPreferencesFile.toString(), Preferences.class);

    }

    public void savePreferences() {
        Gson gson = new Gson();
        gson.toJson(preferences.toString(), Preferences.class);
    }

    public Preferences getPreferences() {
        return this.preferences;
    }

    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
    }

}
