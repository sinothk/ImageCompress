package com.sinothk.helper.image.compress.tiny.common;

import android.util.Log;

import com.sinothk.helper.image.compress.tiny.Tiny;


/**
 * Created by zhengxiaoyong on 2017/3/11.
 */
public final class Logger {

    private static final String TAG = "tiny";

    public static void e(String message) {
        if (Tiny.getInstance().isDebug())
            Log.e(TAG, message);
    }
}
