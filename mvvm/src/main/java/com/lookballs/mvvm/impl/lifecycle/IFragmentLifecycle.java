package com.lookballs.mvvm.impl.lifecycle;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 创建人：lucas
 * 创建时间：2022/2/16 11:01
 * 类描述：Fragment生命周期接口
 */
public interface IFragmentLifecycle {
    void onAttach(@NonNull Activity activity, @NonNull Fragment fragment);

    void onCreate(@NonNull Activity activity, @NonNull Fragment fragment, @Nullable Bundle savedInstanceState);

    void onCreateView(@NonNull Activity activity, @NonNull Fragment fragment);

    void onViewCreated(@NonNull Activity activity, @NonNull Fragment fragment);

    void onActivityCreate(@NonNull Activity activity, @NonNull Fragment fragment);

    void onStart(@NonNull Activity activity, @NonNull Fragment fragment);

    void onResume(@NonNull Activity activity, @NonNull Fragment fragment);

    void onPause(@NonNull Activity activity, @NonNull Fragment fragment);

    void onStop(@NonNull Activity activity, @NonNull Fragment fragment);

    void onSaveInstanceState(@NonNull Activity activity, @NonNull Fragment fragment, @NonNull Bundle outState);

    void onDestroyView(@NonNull Activity activity, @NonNull Fragment fragment);

    void onDestroy(@NonNull Activity activity, @NonNull Fragment fragment);

    void onDetach(@NonNull Activity activity, @NonNull Fragment fragment);
}
