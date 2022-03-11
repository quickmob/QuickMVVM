package com.lookballs.mvvm;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

/**
 * 创建人：lucas
 * 创建时间：2021/5/15 11:06
 * 类描述：Application基类
 */
public class BaseApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        //先于onCreate()执行
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        //程序创建的时候执行
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        //程序终止的时候执行（只在Android设备的模拟器中生效）
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        //低内存的时候执行
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        //程序在内存清理的时候执行
        super.onTrimMemory(level);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        //程序在横竖屏切换的时候执行
        super.onConfigurationChanged(newConfig);
    }
}
