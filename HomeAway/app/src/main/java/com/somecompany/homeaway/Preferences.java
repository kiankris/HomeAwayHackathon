package com.somecompany.homeaway;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by David_000 on 7/15/2017.
 */

public class Preferences {
    @SerializedName("priceMax")
    @Expose
    private int priceMax;

    @SerializedName("priceMin")
    @Expose
    private int priceMin;

    @SerializedName("locations")
    @Expose
    private ArrayList<String> locations;

    @SerializedName("wheelChairAccessible")
    @Expose
    private boolean wheelChairAccessible;

    @SerializedName("petFriendly")
    @Expose
    private boolean petFriendly;

    @SerializedName("numRooms")
    @Expose
    private int numRooms;

    @SerializedName("numBaths")
    @Expose
    private int numBaths;

    @SerializedName("airConditioning")
    @Expose
    private boolean airConditioning;

    public ArrayList<String> getLocations() {
        return locations;
    }
    public boolean removeLocation(String loc){
        return locations.remove(loc);
    }

    public void setLocations(ArrayList<String> locations) {
        this.locations = locations;
    }

    public int getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(int priceMin) {
        this.priceMin = priceMin;
    }

    public int getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(int priceMax) {
        this.priceMax = priceMax;
    }

    public boolean isAirConditioning() {
        return airConditioning;
    }

    public void setAirConditioning(boolean airConditioning) {
        this.airConditioning = airConditioning;
    }

    public int getNumBaths() {
        return numBaths;
    }

    public void setNumBaths(int numBaths) {
        this.numBaths = numBaths;
    }

    public int getNumRooms() {
        return numRooms;
    }

    public void setNumRooms(int numRooms) {
        this.numRooms = numRooms;
    }

    public boolean isPetFriendly() {
        return petFriendly;
    }

    public void setPetFriendly(boolean petFriendly) {
        this.petFriendly = petFriendly;
    }

    public boolean isWheelChairAccessible() {
        return wheelChairAccessible;
    }

    public void setWheelChairAccessible(boolean wheelChairAccessible) {
        this.wheelChairAccessible = wheelChairAccessible;
    }
}
