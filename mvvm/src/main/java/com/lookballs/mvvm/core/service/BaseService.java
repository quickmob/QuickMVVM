package com.lookballs.mvvm.core.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

/**
 * 创建人：lucas
 * 创建时间：2021/5/15 11:06
 * 类描述：Service基类
 * 提示：Service是运行在主线程中的，一般Service我们会开启子线程来执行任务
 */
public abstract class BaseService extends Service {
    /**
     * 子类可以实现的自定义方法
     */
    protected abstract void init();

    private final BaseServiceBinder binder = new BaseServiceBinder();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //每次显式启动服务时都会走
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public class BaseServiceBinder extends Binder {
        public BaseService getService() {
            return BaseService.this;
        }
    }
}
