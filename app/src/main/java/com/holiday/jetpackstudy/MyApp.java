package com.holiday.jetpackstudy;

import android.app.Application;

import androidx.room.Room;

import com.holiday.jetpackstudy.room.AppDatabase;

public class MyApp extends Application {
    private AppDatabase mAppDatabase;
    public static MyApp app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        mAppDatabase = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build();
    }

    public AppDatabase getAppDatabase() {
        return mAppDatabase;
    }
}
