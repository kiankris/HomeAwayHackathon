package com.somecompany.homeaway;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.net.URL;

public class HomeProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_profile);

        TextView title = (TextView) findViewById(R.id.propertyTitle);
        title.setText(Utils.selectedProfile.getLocation());

        TextView location = (TextView) findViewById(R.id.propertyLocation);
        location.setText(Utils.selectedProfile.getTitle());

        ImageView image = (ImageView) findViewById(R.id.propertyPicture);
        String url = Utils.selectedProfile.getImageUrl();
        Glide.with(this).load(url).into(image);

        ((TextView) findViewById(R.id.pricePerNiight)).setText( "Price per night: $" + Integer.toString(
                Utils.selectedProfile.getCost()));
        ((TextView) findViewById(R.id.propertyBathroom)).setText("Number of bathrooms: " + Integer.toString(
                Utils.selectedProfile.getBathrooms()));
        ((TextView) findViewById(R.id.propertyBedroom)).setText("Number of bedrooms: " + Integer.toString(
                Utils.selectedProfile.getBathrooms()));
        String petStatus = (Utils.selectedProfile.isPet()) ? "Yes" : "No";
        ((TextView) findViewById(R.id.propertyPetStatus)).setText("Pet friendly: " + petStatus);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.back);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.save);
        fab2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(v.getContext(),"Saved!",Toast.LENGTH_SHORT).show();
            }
        });

    }

}
