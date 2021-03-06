package com.sinothk.helper.image.compress.tiny.core;

import android.os.Handler;
import android.os.Looper;

import com.sinothk.helper.image.compress.tiny.callback.CallbackDispatcher;

/**
 * Created by zhengxiaoyong on 2017/3/11.
 */
public class MainThreadExecutor {

    private static Handler sMainThreadHandler;

    public static <T> void postToMainThread(final T t, final CallbackDispatcher<T> dispatcher) {
        if (dispatcher == null)
            return;

        enableMainThread();

        sMainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                dispatcher.dispatch(t);
            }
        });
    }

    private static void enableMainThread() {
        if (sMainThreadHandler == null || !isMainThreadHandler(sMainThreadHandler)) {
            sMainThreadHandler = new Handler(Looper.getMainLooper());
        }
    }

    private static boolean isMainThreadHandler(Handler handler) {
        return handler.getLooper().getThread() == Looper.getMainLooper().getThread();
    }
}
