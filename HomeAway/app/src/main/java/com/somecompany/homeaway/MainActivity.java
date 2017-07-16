package com.somecompany.homeaway;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    private SwipePlaceHolderView mSwipeView;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwipeView = (SwipePlaceHolderView)findViewById(R.id.swipeView);
        mContext = getApplicationContext();

        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.tinder_swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.tinder_swipe_out_msg_view));

        //TODO: Make an array of locations, show loading screen when populating array
        //TODO: Give each of them a unique id, add a listener to each(?)
//        for(HouseListing listing : Utils.loadProfiles(this.getApplicationContext())){
//            HomeCard newHome = new HomeCard(mContext, listing, mSwipeView);
//            mSwipeView.addView( newHome);
//            homes.add(newHome);
//        }


        try {
            Utils.loadSettings(this);
        }
        catch(FileNotFoundException e){
            try {
                Utils.saveSettings(this);
            } catch (IOException E) {
                e.printStackTrace();
            }
        }

        ListingManager lm = new ListingManager(getApplicationContext(), Utils.userPreferences);

        for(HouseListing listing : lm.getListings()){
            HomeCard newHome = new HomeCard(mContext, listing, mSwipeView);
            mSwipeView.addView( newHome);
        }


        findViewById(R.id.rejectBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(false);
            }
        });

        findViewById(R.id.acceptBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(true);
            }
        });

        findViewById(R.id.settings_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PreferenceActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        if(Utils.settingsUpdated) {
            Utils.settingsUpdated = false;
            finish();
            startActivity(getIntent());
        }
    }
}
