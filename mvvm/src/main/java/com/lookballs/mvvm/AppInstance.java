package com.lookballs.mvvm;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lookballs.mvvm.impl.lifecycle.IActivityLifecycle;
import com.lookballs.mvvm.impl.lifecycle.IFragmentLifecycle;

import java.util.ArrayList;

/**
 * 创建人：lucas
 * 创建时间：2022/2/16 16:55
 * 类描述：管理提供的一些框架必须的实例
 */
public class AppInstance {
    private volatile static AppInstance instance = null;

    private Application application;
    private ArrayList<IActivityLifecycle> activityLifecycles = new ArrayList<>();
    private ArrayList<IFragmentLifecycle> fragmentLifecycles = new ArrayList<>();

    private AppInstance() {

    }

    private final Application.ActivityLifecycleCallbacks activityLifecycleCallback = new Application.ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
            for (IActivityLifecycle callback : activityLifecycles) {
                callback.onCreate(activity, savedInstanceState);
            }
        }

        @Override
        public void onActivityStarted(@NonNull Activity activity) {
            for (IActivityLifecycle callback : activityLifecycles) {
                callback.onStart(activity);
            }
        }

        @Override
        public void onActivityResumed(@NonNull Activity activity) {
            for (IActivityLifecycle callback : activityLifecycles) {
                callback.onResume(activity);
            }
        }

        @Override
        public void onActivityPaused(@NonNull Activity activity) {
            for (IActivityLifecycle callback : activityLifecycles) {
                callback.onPause(activity);
            }
        }

        @Override
        public void onActivityStopped(@NonNull Activity activity) {
            for (IActivityLifecycle callback : activityLifecycles) {
                callback.onStop(activity);
            }
        }

        @Override
        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
            for (IActivityLifecycle callback : activityLifecycles) {
                callback.onSaveInstanceState(activity, outState);
            }
        }

        @Override
        public void onActivityDestroyed(@NonNull Activity activity) {
            for (IActivityLifecycle callback : activityLifecycles) {
                callback.onDestroy(activity);
            }
        }
    };

    /**
     * 单一实例
     */
    public static AppInstance get() {
        if (instance == null) {
            synchronized (AppInstance.class) {
                if (instance == null) {
                    instance = new AppInstance();
                }
            }
        }
        return instance;
    }

    /**
     * 初始化(必须先于任何方法调用)
     */
    public void init(Application app) {
        this.application = app;
        activityLifecycles.clear();
        fragmentLifecycles.clear();
        app.unregisterActivityLifecycleCallbacks(activityLifecycleCallback);
        app.registerActivityLifecycleCallbacks(activityLifecycleCallback);
    }

    public void setActivityLifecycles(IActivityLifecycle callback) {
        if (callback == null) {
            return;
        }
        synchronized (activityLifecycles) {
            activityLifecycles.add(callback);
        }
    }

    public void setFragmentLifecycles(IFragmentLifecycle callback) {
        if (callback == null) {
            return;
        }
        synchronized (fragmentLifecycles) {
            fragmentLifecycles.add(callback);
        }
    }

    public Application getApp() {
        return application;
    }

    public ArrayList<IActivityLifecycle> getActivityLifecycles() {
        return activityLifecycles;
    }

    public ArrayList<IFragmentLifecycle> getFragmentLifecycles() {
        return fragmentLifecycles;
    }
}
