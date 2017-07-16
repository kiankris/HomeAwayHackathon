package com.somecompany.homeaway;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.MultiSelectListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;

public class PreferenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);
        (findViewById(R.id.save_fab)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Utils.saveSettings(v.getContext());
                    Utils.settingsUpdated = true;
                    Toast.makeText(v.getContext(),"Settings Saved!",Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
                catch (Exception e){

                    Toast.makeText(v.getContext(),"Settings Saving failed!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public static class GeneralPreferenceFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_homeaway);
            EditTextPreference textPref =(EditTextPreference) findPreference("minPrice");
            textPref.setSummary(Integer.toString(Utils.userPreferences.getPriceMin()));
            textPref.setText(Integer.toString(Utils.userPreferences.getPriceMin()));

            textPref = (EditTextPreference) findPreference("maxPrice");
            textPref.setSummary(Integer.toString(Utils.userPreferences.getPriceMax()));
            textPref.setText(Integer.toString(Utils.userPreferences.getPriceMax()));

            HashSet<String> selectedLocations = new HashSet<>();
            for(String s: Utils.userPreferences.getLocations()){
                if(s.equals("LasVegas,Nevada,UnitedStates")){
                    selectedLocations.add("1");
                }
                else if(s.equals("Breckenridge,Colorado,UnitedStates")){
                    selectedLocations.add("3");
                }
                if(s.equals("BigBearLake,California,UnitedStates")){
                    selectedLocations.add("4");
                }
                if(s.equals("Ubud,Bali,RepublicofIndonesia")){
                    selectedLocations.add("5");
                }
                if(s.equals("BeverlyHills,California,UnitedStates")){
                    selectedLocations.add("6");
                }
                if(s.equals("Tokyo,None,Japan")){
                    selectedLocations.add("2");
                }
                if(s.equals("Austin,Texas,UnitedStates")){
                    selectedLocations.add("0");
                }
                if(s.equals("NewYork,NewYork,UnitedStates")){
                    selectedLocations.add("7");
                }
                if(s.equals("Vancouver,BritishColumbia,Canada")){
                    selectedLocations.add("8");
                }
                if(s.equals("Berlin,None,Germany")){
                    selectedLocations.add("9");
                }

                if(s.equals("SanFrancisco,California,UnitedStates")){
                    selectedLocations.add("10");
                }
            }
            MultiSelectListPreference loc = (MultiSelectListPreference)findPreference("mode_repeat");
            loc.setValues(selectedLocations);
            String text = " location";
            if(selectedLocations.size() != 1){
                text += "s";
            }
            loc.setSummary(Integer.toString(selectedLocations.size()) + text + " selected");

            SwitchPreference pref =(SwitchPreference) findPreference("accessibilityRequirement");
            if(Utils.userPreferences.isWheelChairAccessible()){
                pref.setChecked(true);
            }
            else{
                pref.setChecked(false);
            }

            pref =(SwitchPreference) findPreference("petRequirement");
            if (Utils.userPreferences.isPetFriendly()){
                pref.setChecked(true);
            }
            else{
                pref.setChecked(false);
            }

            pref = (SwitchPreference) findPreference("airConRequirement");
            if(Utils.userPreferences.isAirConditioning()){
                pref.setChecked(true);
            }
            else{
                pref.setChecked(false);
            }

            ListPreference listPref = (ListPreference) findPreference("numBedrooms");
            listPref.setValue(Integer.toString(Utils.userPreferences.getNumRooms()-1));

            listPref = (ListPreference) findPreference("numBathrooms");
            listPref.setValue(Integer.toString(Utils.userPreferences.getNumBaths()-1));
        }

        @Override
        public void onResume() {
            super.onResume();
            getPreferenceScreen().getSharedPreferences()
                    .registerOnSharedPreferenceChangeListener(this);
        }

        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
                                              String key) {

            Preference preference = findPreference(key);

            if(key.equals("minPrice")){
                Utils.userPreferences.setPriceMin(Integer.parseInt(sharedPreferences.getString(key,"")));
            }
            else if(key.equals("maxPrice")){
                Utils.userPreferences.setPriceMax(Integer.parseInt(sharedPreferences.getString(key,"")));
            }
            if (preference instanceof EditTextPreference) {
                preference.setSummary(sharedPreferences.getString(key,""));
            }

            if(key.equals("mode_repeat")){
                MultiSelectListPreference locations = (MultiSelectListPreference)preference;
                ArrayList<String> newLocations = new ArrayList<>();
                for(String s: locations.getValues()){
                    if(s.equals("1")){
                        newLocations.add("LasVegas,Nevada,UnitedStates");
                    }
                    if(s.equals("3")){
                        newLocations.add("Breckenridge,Colorado,UnitedStates");
                    }
                    if(s.equals("4")){
                        newLocations.add("BigBearLake,California,UnitedStates");
                    }
                    if(s.equals("5")){
                        newLocations.add("Ubud,Bali,RepublicofIndonesia");
                    }
                    if(s.equals("6")){
                        newLocations.add("BeverlyHills,California,UnitedStates");
                    }
                    if(s.equals("2")){
                        newLocations.add("Tokyo,None,Japan");
                    }
                    if(s.equals("0")){
                        newLocations.add("Austin,Texas,UnitedStates");
                    }
                    if(s.equals("7")){
                        newLocations.add("NewYork,NewYork,UnitedStates");
                    }
                    if(s.equals("8")){
                        newLocations.add("Vancouver,BritishColumbia,Canada");
                    }
                    if(s.equals("9")){
                        newLocations.add("Berlin,None,Germany");
                    }

                    if(s.equals("10")){
                        newLocations.add("SanFrancisco,California,UnitedStates");
                    }
                }
                Utils.userPreferences.setLocations(newLocations);
                String text = " location";
                if(Utils.userPreferences.getLocations().size() != 1){
                    text += "s";
                }
                locations.setSummary(Integer.toString(Utils.userPreferences.getLocations().size()) + text + " selected");
            }

            if(key.equals("accessibilityRequirement")){
                Utils.userPreferences.setWheelChairAccessible(((SwitchPreference)preference).isChecked());
            }
            if(key.equals("petRequirement")){
                Utils.userPreferences.setPetFriendly(((SwitchPreference)preference).isChecked());
            }
            if(key.equals("airConRequirement")){
                Utils.userPreferences.setAirConditioning(((SwitchPreference)preference).isChecked());
            }

            if(key.equals("numBedrooms")){
                Utils.userPreferences.setNumRooms(Integer.parseInt(((ListPreference)preference).getValue())+1);
            }
            if(key.equals("numBathrooms")){
                Utils.userPreferences.setNumBaths(Integer.parseInt(((ListPreference)preference).getValue())+1);
            }


        }
    }
}
