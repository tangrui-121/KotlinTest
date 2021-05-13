package com.person.kotlintest.rxandroid;

import android.os.Handler;
import android.os.HandlerThread;

/**
 * @anthor tr
 * @date 2021/5/12
 * @desc
 */
public class Timer {
    private static final Timer INSTANCE = new Timer();
    private Handler mHandler;

    private Timer() {
        HandlerThread thread = new HandlerThread("timer");
        thread.start();
        mHandler = new Handler(thread.getLooper());
    }

    public static Timer get() {
        return INSTANCE;
    }

    public void post(Runnable runnable) {
        mHandler.post(runnable);
    }

    public void postDelayed(Runnable runnable, long delay) {
        mHandler.postDelayed(runnable, delay);
    }
}