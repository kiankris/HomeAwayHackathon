package com.somecompany.homeaway;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeInState;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState;

/**
 * Created by kushantha on 7/15/17.
 */

@Layout(R.layout.tinder_card_view)
public class HomeCard {

        @View(R.id.profileImageView)
        private ImageView profileImageView;

        @View(R.id.nameAgeTxt)
        private TextView nameAgeTxt;

        @View(R.id.locationNameTxt)
        private TextView locationNameTxt;

        public Profile mProfile;
        private Context mContext;
        private SwipePlaceHolderView mSwipeView;

        public HomeCard(Context context, Profile profile, SwipePlaceHolderView swipeView) {
            mContext = context;
            mProfile = profile;
            mSwipeView = swipeView;
        }

        @Resolve
        private void onResolved(){
            Glide.with(mContext).load(mProfile.getImageUrl()).into(profileImageView);
            nameAgeTxt.setText(mProfile.getName() + ", " + mProfile.getAge());
            locationNameTxt.setText(mProfile.getLocation());
        }

        @SwipeOut
        private void onSwipedOut(){
            Log.d("EVENT", "onSwipedOut");
            mSwipeView.addView(this);
        }

        @SwipeCancelState
        private void onSwipeCancelState(){
            Log.d("EVENT", "onSwipeCancelState");
        }

        @SwipeIn
        private void onSwipeIn(){
            Log.d("EVENT", "onSwipedIn");
        }

        @SwipeInState
        private void onSwipeInState(){
            Log.d("EVENT", "onSwipeInState");
        }

        @SwipeOutState
        private void onSwipeOutState(){
            Log.d("EVENT", "onSwipeOutState");
        }

        @Click(R.id.cardHolder)
        private void onClick(){
            Utils.selectedProfile = mProfile;
            Intent profilePage = new Intent(mContext, HomeProfileActivity.class);
            mContext.startActivity(profilePage);
        }

}
