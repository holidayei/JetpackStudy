package com.holiday.jetpackstudy.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.holiday.jetpackstudy.utils.QrLog;

public class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QrLog.e(getClass().getSimpleName() + " - onCreate , " + getLifecycle().getCurrentState());
    }

    @Override
    protected void onStart() {
        super.onStart();
        QrLog.e(getClass().getSimpleName() + " - onStart , " + getLifecycle().getCurrentState());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        QrLog.e(getClass().getSimpleName() + " - onRestart , " + getLifecycle().getCurrentState());
    }

    @Override
    protected void onResume() {
        super.onResume();
        QrLog.e(getClass().getSimpleName() + " - onResume , " + getLifecycle().getCurrentState());
    }

    @Override
    protected void onPause() {
        super.onPause();
        QrLog.e(getClass().getSimpleName() + " - onPause , " + getLifecycle().getCurrentState());
    }

    @Override
    protected void onStop() {
        super.onStop();
        QrLog.e(getClass().getSimpleName() + " - onStop , " + getLifecycle().getCurrentState());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        QrLog.e(getClass().getSimpleName() + " - onDestroy , " + getLifecycle().getCurrentState());
    }
}
