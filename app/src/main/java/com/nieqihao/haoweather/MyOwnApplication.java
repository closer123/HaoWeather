package com.nieqihao.haoweather;

import android.app.Application;

import org.litepal.LitePal;

public class MyOwnApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }
}
