package com.somecompany.homeaway;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kushantha on 7/15/17.
 */

public class HouseListing implements Comparable<HouseListing>{
    @SerializedName("url")
    @Expose
    private String imageUrl;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("cost")
    @Expose
    private Integer cost;

    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("bedrooms")
    @Expose
    private Integer bedrooms;

    @SerializedName("bathrooms")
    @Expose
    private Integer bathrooms;

    @SerializedName("pet")
    @Expose
    private boolean pet;

    private int rank;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }


    public Integer getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(Integer bedrooms) {
        this.bedrooms = bedrooms;
    }

    public Integer getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(Integer bathrooms) {
        this.bathrooms = bathrooms;
    }

    public boolean isPet() {
        return pet;
    }

    public void setPet(boolean pet) {
        this.pet = pet;
    }

    public int getRank() { return rank; }

    public void setRank(Preferences p) {
        int rank = 0;
        String sterilizedLocation = this.location.replaceAll("\\s+","");
        if(p.getLocations().contains(sterilizedLocation)){
            rank+= 7;
        }
        if(p.getPriceMin() < this.cost && this.cost <= p.getPriceMax()){
            rank++;
        }
        if(p.getNumRooms() <= this.bedrooms){
            rank++;
        }
        if(p.getNumBaths() <= this.getBathrooms()){
            rank++;
        }
        if(p.isPetFriendly() == this.pet) {
            rank++;
        }
        this.rank = rank;
    }

    public int compareTo(HouseListing hl){
     if(this.getRank() < hl.getRank()){
         return 1;
     }
     else if(this.getRank() > hl.getRank()){
         return -1;
     }
     return 0;
    }
}
