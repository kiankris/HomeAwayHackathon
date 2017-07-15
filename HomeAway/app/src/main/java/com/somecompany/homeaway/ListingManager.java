package com.somecompany.homeaway;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ListingManager {
    private List<Listing> liked, disliked;

    ListingManager(){
        liked = new LinkedList<>();
        disliked = new LinkedList<>();
    }

    boolean liked(Listing elem){
        return liked.add(elem);
    }

    boolean disliked(E elem){
        return disliked.add(elem);
    }
}