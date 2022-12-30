package com.azissulaiman.eder.fragments;

import android.net.wifi.aware.PublishConfig;

import java.lang.reflect.Constructor;

public class ScreenItem {
    String Title, Description;
    int Gambar;

    public ScreenItem (String title, String description, int gambar){
        Title = title;
        Description = description;
        Gambar = gambar;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public int getGambar() {
        return Gambar;
    }
}
