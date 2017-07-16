package com.somecompany.homeaway;

import android.content.Context;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class ListingManager {
    private List<HouseListing> liked;
    private List<HouseListing> disliked;
    private PriorityQueue<HouseListing> listings;

    public PriorityQueue<HouseListing> getListings() {
        return listings;
    }

    ListingManager(Context applicationContext, Preferences preferences){
        this.liked = new LinkedList<>();
        this.disliked = new LinkedList<>();
        this.listings = new PriorityQueue<HouseListing>();
        for(HouseListing houseListing: Utils.loadProfiles(applicationContext)) {
            houseListing.setRank(preferences);
            listings.add(houseListing);
        }
    }

    boolean liked(HouseListing elem){
        return liked.add(elem);
    }

    boolean disliked(HouseListing elem){
        return disliked.add(elem);
    }


}