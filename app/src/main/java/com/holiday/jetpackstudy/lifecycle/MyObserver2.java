package com.holiday.jetpackstudy.lifecycle;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

/**
 * 实现DefaultLifecycleObserver接口，推荐
 */
public class MyObserver2 implements DefaultLifecycleObserver {

    private static final String TAG = "Lifecycle";

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        Log.e(TAG, "Lifecycle2 call onResume");
    }

    @Override
    public void onPause(@NonNull LifecycleOwner owner) {
        Log.e(TAG, "Lifecycle2 call onPause");
    }

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        Log.e(TAG, "Lifecycle2 call onCreate");
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        Log.e(TAG, "Lifecycle2 call onDestroy");
    }

    //如果不想全部实现，可以自己写一个simple类；或启用java8，因为需要支持DefaultLifecycleObserver接口的默认实现
}
