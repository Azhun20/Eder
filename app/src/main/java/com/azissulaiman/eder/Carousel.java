package com.azissulaiman.eder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.azissulaiman.eder.fragments.IntroView;
import com.azissulaiman.eder.fragments.ScreenItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Carousel extends AppCompatActivity {

    private ViewPager screenPager;
    IntroView introView;
    TabLayout tabIndicator;
    Button btnstart;
    int position = 4;
    TextView txt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carousel);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        tabIndicator = findViewById(R.id.tab1);
        btnstart = findViewById(R.id.btnmulai);
        txt1 = findViewById(R.id.txtskip);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().hide();

        final List<ScreenItem> mlist = new ArrayList<>();
        mlist.add(new ScreenItem("Discover \n1+ Milion of Books","Rich your knowledge power and discover new world",R.drawable.onboarding1));
        mlist.add(new ScreenItem("Create your own\nLibrary","Fill your own Library with your favorite book",R.drawable.onboarding2));
        mlist.add(new ScreenItem("Earn rewards by\nReading","Do daily task to gain points and get your rewards",R.drawable.onboarding3));
        mlist.add(new ScreenItem("Reach the top of the\nLeaderboard","Daily reading habit will get you to the leaderboard",R.drawable.onboarding4));
        mlist.add(new ScreenItem("Track progress\ntogether","Your reading progress automatically updated",R.drawable.onboarding5));

        screenPager= findViewById(R.id.viewScreen);
        introView = new IntroView(this,mlist);
        screenPager.setAdapter(introView);

        tabIndicator.setupWithViewPager(screenPager);
        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadscreen();

            }
        });
        tabIndicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == mlist.size()-1){
                    loadscreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Carousel.this, LoginActivity.class));
            }
        });

    }
    private void loadscreen(){

        txt1.setVisibility(View.INVISIBLE);
        btnstart.setVisibility(View.VISIBLE);
    }
}