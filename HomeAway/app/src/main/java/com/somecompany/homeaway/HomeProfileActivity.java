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
        title.setText(Utils.selectedProfile.getName());

        TextView location = (TextView) findViewById(R.id.propertyLocation);
        location.setText(Utils.selectedProfile.getLocation());

        ImageView image = (ImageView) findViewById(R.id.propertyPicture);
        String url = Utils.selectedProfile.getImageUrl();
        Glide.with(this).load(url).into(image);

        ((TextView) findViewById(R.id.pricePerNiight)).setText("Price Per night = 300");
        ((TextView) findViewById(R.id.propertyBathroom)).setText("Num Bathrooms = 2");
        ((TextView) findViewById(R.id.propertyBedroom)).setText("Num Bedrooms = 2");
        ((TextView) findViewById(R.id.propertyPetStatus)).setText("Property is pet friendly");

        /*try{


            Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(Utils.selectedProfile.getImageUrl()).getContent());
            image.setImageBitmap(bitmap);
        }
        catch(Exception e){}*/

    }

}
