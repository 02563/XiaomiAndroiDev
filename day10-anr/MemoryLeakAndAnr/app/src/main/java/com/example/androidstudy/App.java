package com.example.androidstudy;

import android.app.Application;

import com.github.anrwatchdog.ANRWatchDog;

public class App extends Application {
    @Override
    public void onCreate() {
        new ANRWatchDog().start();
        super.onCreate();
    }
}
