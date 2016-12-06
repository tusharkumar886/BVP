package com.hungryhackers.bvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private int menuSelection = 0, prevMenu = 0;
    private ImageView eventsImage, profileImage, timeTableImage,eventImageSelect, profileImageSelect, timeTableImageSelect;
    private LinearLayout eventSelect, profileSelect, timetableSelect;
    private Animation slideOutLeft, slideOutRight, slideInLeft, slideInRight, fadeIn;
//    private TextView actionBarTitle = (TextView) findViewById(R.id.action_bar_title);
    private RelativeLayout profileLayout, eventsLayout, timeTableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        eventsImage = (ImageView) findViewById(R.id.eventsListener);
        profileImage = (ImageView) findViewById(R.id.profileListener);
        timeTableImage = (ImageView) findViewById(R.id.timetableListener);
        eventSelect = (LinearLayout) findViewById(R.id.eventLine);
        profileSelect = (LinearLayout) findViewById(R.id.profileLine);
        timetableSelect = (LinearLayout) findViewById(R.id.timetableLine);
        slideOutLeft = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slideoutleft);
        slideOutRight = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slideoutright);
        slideInLeft = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slideinleft);
        slideInRight = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slideinright);
        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
        profileLayout = (RelativeLayout) findViewById(R.id.profile_layout);
        eventsLayout = (RelativeLayout) findViewById(R.id.events_layout);
        timeTableLayout = (RelativeLayout) findViewById(R.id.timetable_layout);

        profileImage.setImageResource(R.drawable.profileicon_selected);
        profileSelect.setVisibility(View.VISIBLE);
        timeTableImage.setImageResource(R.drawable.timetableicon);
        eventsImage.setImageResource(R.drawable.eventsicon);
        eventSelect.setVisibility(View.INVISIBLE);
        timetableSelect.setVisibility(View.INVISIBLE);
        profileLayout.setVisibility(View.VISIBLE);
        eventsLayout.setVisibility(View.GONE);
        timeTableLayout.setVisibility(View.GONE);

    }

    public void eventsListener (View view){
        menuSelection = -1;
        menuChangeAnimation();
        setLayoutVisibilty();
    }

    public void profileListener (View view){
        menuSelection = 0;
        menuChangeAnimation();
        setLayoutVisibilty();
    }

    public void timeTableListener (View view){
        menuSelection = 1;
        menuChangeAnimation();
        setLayoutVisibilty();
    }

    protected void menuChangeAnimation (){
        switch (menuSelection){
            case -1:
//                actionBarTitle.setText("Events");
                eventsImage.setImageResource(R.drawable.eventsicon_selected);
                eventSelect.setVisibility(View.VISIBLE);
                if (prevMenu == 0) {
                    eventSelect.startAnimation(slideInRight);
                    profileSelect.startAnimation(slideOutLeft);
                }
                else if (prevMenu == 1){
                    eventSelect.startAnimation(slideInLeft);
                    timetableSelect.startAnimation(slideOutRight);
                }
                profileImage.setImageResource(R.drawable.profileicon);
                timeTableImage.setImageResource(R.drawable.timetableicon);
                break;
            case 0:
//                actionBarTitle.setText("Profile");
                profileImage.setImageResource(R.drawable.profileicon_selected);
                if (prevMenu == -1) {
                    profileSelect.startAnimation(slideInLeft);
                    eventSelect.startAnimation(slideOutRight);
                }
                else if (prevMenu == 1){
                    profileSelect.startAnimation(slideInRight);
                    timetableSelect.startAnimation(slideOutLeft);
                }
                eventsImage.setImageResource(R.drawable.eventsicon);
                timeTableImage.setImageResource(R.drawable.timetableicon);
                break;
            case 1:
//                actionBarTitle.setText("Time Table");
                timeTableImage.setImageResource(R.drawable.timetableicon_selected);
                timetableSelect.setVisibility(View.VISIBLE);
                if (prevMenu == -1) {
                    timetableSelect.startAnimation(slideInRight);
                    eventSelect.startAnimation(slideOutLeft);
                }
                else if (prevMenu == 0){
                    timetableSelect.startAnimation(slideInLeft);
                    profileSelect.startAnimation(slideOutRight);
                }
                eventsImage.setImageResource(R.drawable.eventsicon);
                profileImage.setImageResource(R.drawable.profileicon);
        }
        prevMenu = menuSelection;
    }

    protected void setLayoutVisibilty(){
        switch (menuSelection){
            case -1:
                eventsLayout.bringToFront();
                eventsLayout.setVisibility(View.VISIBLE);
                eventsLayout.startAnimation(fadeIn);
                profileLayout.setVisibility(View.GONE);
                timeTableLayout.setVisibility(View.GONE);
                break;
            case 0:
                profileLayout.bringToFront();
                profileLayout.setVisibility(View.VISIBLE);
                profileLayout.startAnimation(fadeIn);
                eventsLayout.setVisibility(View.GONE);
                timeTableLayout.setVisibility(View.GONE);
                break;
            case 1:
//                timeTableLayout.bringToFront();
//                timeTableLayout.setVisibility(View.VISIBLE);
//                timeTableLayout.startAnimation(fadeIn);
//                eventsLayout.setVisibility(View.GONE);
//                profileLayout.setVisibility(View.GONE);
                Intent intent = new Intent(MainActivity.this,TimeTable.class);
                startActivity(intent);

        }
    }
}
