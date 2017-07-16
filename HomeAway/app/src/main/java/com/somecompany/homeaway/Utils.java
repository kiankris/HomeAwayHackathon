package com.somecompany.homeaway;

import android.content.Context;
import android.content.res.AssetManager;
import android.provider.ContactsContract;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by kushantha on 7/15/17.
 */

public class Utils {
    private static final String TAG = "Utils";
    public static HouseListing selectedProfile;
    public static Preferences userPreferences = new Preferences();
    public static File prefFile;
    public static Gson gson = new Gson();
    public static boolean settingsUpdated = false;

    public static List<HouseListing> loadProfiles(Context context){
        try{
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            //JSONArray array = new JSONArray(loadJSONFromAsset(context, "profiles.json"));
            JSONArray array = new JSONArray(loadJSONFromAsset(context, "houses.json"));
            List<HouseListing> houseList = new ArrayList<>();
            for(int i=0;i<array.length();i++){
                HouseListing profile = gson.fromJson(array.getString(i), HouseListing.class);
                houseList.add(profile);
            }
            return houseList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void loadSettings(Context context) throws FileNotFoundException{
        prefFile = new File(context.getFilesDir(), "preferences.json");
        Scanner s = new Scanner(prefFile);
        String test = "";
        while(s.hasNext()){test +=s.next();}
        userPreferences = gson.fromJson(test,Preferences.class);
        s.close();
    }

    public static void saveSettings(Context context) throws IOException{
        prefFile = new File(context.getFilesDir(), "preferences.json");
        FileWriter stream = new FileWriter(prefFile);
        gson.toJson(userPreferences,stream);
        stream.flush();
        stream.close();
    }

    private static String loadJSONFromAsset(Context context, String jsonFileName) {
        String json = null;
        InputStream is=null;
        try {
            AssetManager manager = context.getAssets();
            Log.d(TAG,"path "+jsonFileName);
            is = manager.open(jsonFileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
