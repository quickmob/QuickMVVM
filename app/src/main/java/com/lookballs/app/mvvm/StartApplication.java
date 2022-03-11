package com.lookballs.app.mvvm;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.LogUtils;
import com.lookballs.app.mvvm.http.CustomOkHttpClient;
import com.lookballs.app.mvvm.http.converter.DataConverter;
import com.lookballs.http.HttpConfig;
import com.lookballs.http.QuickHttp;
import com.lookballs.http.core.listener.OnRetryConditionListener;
import com.lookballs.mvvm.AppInstance;
import com.lookballs.mvvm.core.BaseApplication;
import com.lookballs.mvvm.impl.lifecycle.IActivityLifecycle;
import com.lookballs.mvvm.impl.lifecycle.IFragmentLifecycle;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import okhttp3.Dns;

public class StartApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        initOkhttpConfig(this);
        initFrameConfig();
    }

    /**
     * 初始化Okhttp网络请求配置20210513
     */
    private void initOkhttpConfig(Context mContext) {
        CustomOkHttpClient.getInstance().createOkHttpClient(15000, Dns.SYSTEM);

        HttpConfig.Builder builder = new HttpConfig.Builder();
        builder.setLogEnabled(true);
        builder.setHttpClient(CustomOkHttpClient.getInstance().getOkHttpClient());
        builder.setDataConverter(new DataConverter());
        builder.setRetry(2, 1000, new OnRetryConditionListener() {
            @Override
            public boolean retryCondition(Exception e) {
                return e instanceof SocketTimeoutException || e instanceof UnknownHostException;
            }
        });
        QuickHttp.init(builder.build(mContext));
    }

    /**
     * 基础框架配置
     */
    private void initFrameConfig() {
        AppInstance.get().setActivityLifecycles(new IActivityLifecycle() {
            @Override
            public void onCreate(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                LogUtils.e("AppInstance", "activity onCreate：" + activity);
            }

            @Override
            public void onStart(@NonNull Activity activity) {
                LogUtils.e("AppInstance", "activity onStart：" + activity);
            }

            @Override
            public void onResume(@NonNull Activity activity) {
                LogUtils.e("AppInstance", "activity onResume：" + activity);
            }

            @Override
            public void onPause(@NonNull Activity activity) {
                LogUtils.e("AppInstance", "activity onPause：" + activity);
            }

            @Override
            public void onStop(@NonNull Activity activity) {
                LogUtils.e("AppInstance", "activity onStop：" + activity);
            }

            @Override
            public void onRestart(@NonNull Activity activity) {
                LogUtils.e("AppInstance", "activity onRestart：" + activity);
            }

            @Override
            public void onSaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
                LogUtils.e("AppInstance", "activity onSaveInstanceState：" + activity);
            }

            @Override
            public void onDestroy(@NonNull Activity activity) {
                LogUtils.e("AppInstance", "activity onDestroy：" + activity);
            }
        });
        AppInstance.get().setFragmentLifecycles(new IFragmentLifecycle() {
            @Override
            public void onAttach(@NonNull Activity activity, @NonNull Fragment fragment) {
                LogUtils.e("AppInstance", "fragment onAttach：" + fragment);
            }

            @Override
            public void onCreate(@NonNull Activity activity, @NonNull Fragment fragment, @Nullable Bundle savedInstanceState) {
                LogUtils.e("AppInstance", "fragment onCreate：" + fragment);
            }

            @Override
            public void onCreateView(@NonNull Activity activity, @NonNull Fragment fragment) {
                LogUtils.e("AppInstance", "fragment onCreateView：" + fragment);
            }

            @Override
            public void onViewCreated(@NonNull Activity activity, @NonNull Fragment fragment) {
                LogUtils.e("AppInstance", "fragment onViewCreated：" + fragment);
            }

            @Override
            public void onActivityCreate(@NonNull Activity activity, @NonNull Fragment fragment) {
                LogUtils.e("AppInstance", "fragment onActivityCreate：" + fragment);
            }

            @Override
            public void onStart(@NonNull Activity activity, @NonNull Fragment fragment) {
                LogUtils.e("AppInstance", "fragment onStart：" + fragment);
            }

            @Override
            public void onResume(@NonNull Activity activity, @NonNull Fragment fragment) {
                LogUtils.e("AppInstance", "fragment onResume：" + fragment);
            }

            @Override
            public void onPause(@NonNull Activity activity, @NonNull Fragment fragment) {
                LogUtils.e("AppInstance", "fragment onPause：" + fragment);
            }

            @Override
            public void onStop(@NonNull Activity activity, @NonNull Fragment fragment) {
                LogUtils.e("AppInstance", "fragment onStop：" + fragment);
            }

            @Override
            public void onSaveInstanceState(@NonNull Activity activity, @NonNull Fragment fragment, @NonNull Bundle outState) {
                LogUtils.e("AppInstance", "fragment onSaveInstanceState：" + fragment);
            }

            @Override
            public void onDestroyView(@NonNull Activity activity, @NonNull Fragment fragment) {
                LogUtils.e("AppInstance", "fragment onDestroyView：" + fragment);
            }

            @Override
            public void onDestroy(@NonNull Activity activity, @NonNull Fragment fragment) {
                LogUtils.e("AppInstance", "fragment onDestroy：" + fragment);
            }

            @Override
            public void onDetach(@NonNull Activity activity, @NonNull Fragment fragment) {
                LogUtils.e("AppInstance", "fragment onDetach：" + fragment);
            }
        });
    }
}
