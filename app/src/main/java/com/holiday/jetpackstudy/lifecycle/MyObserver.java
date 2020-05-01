package com.holiday.jetpackstudy.lifecycle;


import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * 用注解的方式实现，不再推荐
 */
public class MyObserver implements LifecycleObserver {

    private static final String TAG = "Lifecycle";

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onResume() {
        Log.e(TAG, "Lifecycle call onResume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPause() {
        Log.e(TAG, "Lifecycle call onPause");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {
        Log.e(TAG, "Lifecycle call onCreate");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy() {
        Log.e(TAG, "Lifecycle call onDestroy");
    }
}
