package com.holiday.jetpackstudy.utils;

import android.widget.Toast;

import com.holiday.jetpackstudy.MyApp;

public class QrToast {
    public static void show(String s) {
        Toast.makeText(MyApp.app, s, Toast.LENGTH_SHORT).show();
    }
}
