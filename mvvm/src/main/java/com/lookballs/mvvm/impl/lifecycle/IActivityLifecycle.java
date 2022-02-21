package com.lookballs.mvvm.impl.lifecycle;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 创建人：lucas
 * 创建时间：2022/2/16 11:01
 * 类描述：Activity生命周期接口
 */
public interface IActivityLifecycle {
    void onCreate(@NonNull Activity activity, @Nullable Bundle savedInstanceState);

    void onStart(@NonNull Activity activity);

    void onResume(@NonNull Activity activity);

    void onPause(@NonNull Activity activity);

    void onStop(@NonNull Activity activity);

    void onRestart(@NonNull Activity activity);

    void onSaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState);

    void onDestroy(@NonNull Activity activity);
}
