package com.holiday.jetpackstudy.utils;

import android.util.Log;

public class QrLog {

    private static boolean DEBUG = true;
    private static final String TAG = "哈利迪";

    public static void e(String s) {
        if (DEBUG) Log.e(TAG, s);
    }

    public static void e(String tag, String s) {
        if (DEBUG) Log.e(tag, s);
    }
}
