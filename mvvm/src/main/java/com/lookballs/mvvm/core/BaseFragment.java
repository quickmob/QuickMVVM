package com.lookballs.mvvm.core;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;

import com.lookballs.mvvm.AppInstance;
import com.lookballs.mvvm.impl.action.BundleAction;
import com.lookballs.mvvm.impl.action.ClickAction;
import com.lookballs.mvvm.impl.action.HandlerAction;
import com.lookballs.mvvm.impl.action.KeyboardAction;
import com.lookballs.mvvm.impl.action.ResourcesAction;
import com.lookballs.mvvm.impl.lifecycle.IFragmentLifecycle;

import java.util.List;

/**
 * 创建人：lucas
 * 创建时间：2021/5/15 11:06
 * 类描述：Fragment基类(可以懒加载)
 */
public abstract class BaseFragment extends Fragment implements HandlerAction, ClickAction, KeyboardAction, ResourcesAction, BundleAction {
    /**
     * 缓存视图，如果视图已经创建，则不再初始化视图
     */
    private View rootView = null;

    /**
     * activity对象
     */
    private FragmentActivity activity = null;

    /**
     * 页面是否显示
     */
    private boolean isShowing = false;

    /**
     * 这个Fragment是否已经加载过了
     */
    private boolean isLoaded;

    /**
     * 子类可以实现的自定义方法
     */
    public void onFragmentResume(boolean firstLoad) {

    }

    public void initContentView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        rootView = inflater.inflate(getLayoutId(), container, false);
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = requireActivity();
        if (isLifecycleCallback()) {
            for (IFragmentLifecycle callback : AppInstance.get().getFragmentLifecycles()) {
                callback.onAttach(getAct(), this);
            }
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isLifecycleCallback()) {
            for (IFragmentLifecycle callback : AppInstance.get().getFragmentLifecycles()) {
                callback.onCreate(getAct(), this, savedInstanceState);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView != null) {
            if (isLifecycleCallback()) {
                for (IFragmentLifecycle callback : AppInstance.get().getFragmentLifecycles()) {
                    callback.onCreateView(getAct(), this);
                }
            }
            return rootView;
        }
        initContentView(inflater, container);
        initView();
        initOther();
        if (isLifecycleCallback()) {
            for (IFragmentLifecycle callback : AppInstance.get().getFragmentLifecycles()) {
                callback.onCreateView(getAct(), this);
            }
        }
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tryInitData();
        if (isLifecycleCallback()) {
            for (IFragmentLifecycle callback : AppInstance.get().getFragmentLifecycles()) {
                callback.onViewCreated(getAct(), this);
            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isLifecycleCallback()) {
            for (IFragmentLifecycle callback : AppInstance.get().getFragmentLifecycles()) {
                callback.onActivityCreate(getAct(), this);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (isLifecycleCallback()) {
            for (IFragmentLifecycle callback : AppInstance.get().getFragmentLifecycles()) {
                callback.onStart(getAct(), this);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        isShowing = true;
        tryInitData();
        if (isLifecycleCallback()) {
            for (IFragmentLifecycle callback : AppInstance.get().getFragmentLifecycles()) {
                callback.onResume(getAct(), this);
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        isShowing = false;
        if (isLifecycleCallback()) {
            for (IFragmentLifecycle callback : AppInstance.get().getFragmentLifecycles()) {
                callback.onPause(getAct(), this);
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (isLifecycleCallback()) {
            for (IFragmentLifecycle callback : AppInstance.get().getFragmentLifecycles()) {
                callback.onStop(getAct(), this);
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (isLifecycleCallback()) {
            for (IFragmentLifecycle callback : AppInstance.get().getFragmentLifecycles()) {
                callback.onSaveInstanceState(getAct(), this, outState);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        destroyResources();
        if (isLifecycleCallback()) {
            for (IFragmentLifecycle callback : AppInstance.get().getFragmentLifecycles()) {
                callback.onDestroy(getAct(), this);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isLoaded = false;
        rootView = null;
        if (isLifecycleCallback()) {
            for (IFragmentLifecycle callback : AppInstance.get().getFragmentLifecycles()) {
                callback.onDestroyView(getAct(), this);
            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (isLifecycleCallback()) {
            for (IFragmentLifecycle callback : AppInstance.get().getFragmentLifecycles()) {
                callback.onDetach(getAct(), this);
            }
        }
        activity = null;
    }

    private void initOther() {

    }

    private void tryInitData() {
        if (isLazyLoad()) {
            if (!isLoaded) {
                initData();
                isLoaded = true;
                onFragmentResume(true);
                return;
            }
            onFragmentResume(false);
        } else {
            initData();
        }
    }

    public FragmentActivity getAct() {
        return activity;
    }

    public View getRootView() {
        return rootView;
    }

    public void setRootView(View view) {
        this.rootView = view;
    }

    @Override
    public Context getContext() {
        return activity;
    }

    @Override
    public Bundle getBundle() {
        return getArguments();
    }

    @Override
    public void onClick(View view) {

    }

    /**
     * 是否开启懒加载，默认开启
     */
    public boolean isLazyLoad() {
        return true;
    }

    /**
     * 是否回调生命周期，默认回调
     */
    public boolean isLifecycleCallback() {
        return true;
    }

    /**
     * 这个Fragment是否已经加载过了
     */
    public boolean isLoaded() {
        return isLoaded;
    }

    /**
     * 页面是否显示
     */
    public boolean isShowing() {
        return isShowing;
    }

    /**
     * 根据资源 id 获取一个 View 对象
     */
    @Override
    public <V extends View> V findViewById(@IdRes int id) {
        return getRootView().findViewById(id);
    }

    /**
     * 销毁当前 Fragment 所在的 Activity
     */
    public void finish() {
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        activity.finish();
    }

    /**
     * 回收资源
     */
    private void destroyResources() {
        removeCallbacks();
    }

    /**
     * startActivityForResult 方法优化
     */
    public void openActivityForResult(Class<? extends Activity> clazz, BaseActivity.OnActivityCallback callback) {
        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).openActivityForResult(clazz, null, callback);
        }
    }

    public void openActivityForResult(Class<? extends Activity> clazz, @Nullable Bundle options, BaseActivity.OnActivityCallback callback) {
        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).openActivityForResult(clazz, options, callback);
        }
    }

    public void openActivityForResult(Intent intent, BaseActivity.OnActivityCallback callback) {
        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).openActivityForResult(intent, null, callback);
        }
    }

    public void openActivityForResult(Intent intent, @Nullable Bundle options, BaseActivity.OnActivityCallback callback) {
        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).openActivityForResult(intent, options, callback);
        }
    }

    public void openActivityForResult(Intent intent, @Nullable Bundle options, int requestCode, BaseActivity.OnActivityCallback callback) {
        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).openActivityForResult(intent, options, requestCode, callback);
        }
    }

    /**
     * Fragment 按键事件派发
     */
    public boolean dispatchKeyEvent(KeyEvent event) {
        List<Fragment> fragments = getChildFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            // 这个子 Fragment 必须是 BaseFragment 的子类，并且处于可见状态
            if (!(fragment instanceof BaseFragment) ||
                    fragment.getLifecycle().getCurrentState() != Lifecycle.State.RESUMED) {
                continue;
            }
            // 将按键事件派发给子 Fragment 进行处理
            if (((BaseFragment) fragment).dispatchKeyEvent(event)) {
                // 如果子 Fragment 拦截了这个事件，那么就不交给父 Fragment 处理
                return true;
            }
        }
        switch (event.getAction()) {
            case KeyEvent.ACTION_DOWN:
                return onKeyDown(event.getKeyCode(), event);
            case KeyEvent.ACTION_UP:
                return onKeyUp(event.getKeyCode(), event);
            default:
                return false;
        }
    }

    /**
     * 按键按下事件回调
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 默认不拦截按键事件
        return false;
    }

    /**
     * 按键抬起事件回调
     */
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        // 默认不拦截按键事件
        return false;
    }
}
