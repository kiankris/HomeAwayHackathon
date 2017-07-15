package com.somecompany.homeaway;

import android.media.Image;

import java.util.ArrayList;

/**
 * Created by David_000 on 7/15/2017.
 */

public class Listing {
    private Image propertyImage;
    private ListingDetail propertyDetail;
    private Integer propertyId;


    public Image getPropertyImage() {
        return propertyImage;
    }

    public void setPropertyImage(Image propertyImage) {
        this.propertyImage = propertyImage;
    }

    public ListingDetail getPropertyDetail() {
        return propertyDetail;
    }

    public void setPropertyDetail(ListingDetail propertyDetail) {
        this.propertyDetail = propertyDetail;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }
}
