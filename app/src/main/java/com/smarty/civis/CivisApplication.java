package com.smarty.civis;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by diaa on 6/28/2017.
 */

public class CivisApplication extends Application {
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}