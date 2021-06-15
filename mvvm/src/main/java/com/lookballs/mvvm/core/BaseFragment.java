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

import com.lookballs.mvvm.BaseEvent;
import com.lookballs.mvvm.action.BundleAction;
import com.lookballs.mvvm.action.ClickAction;
import com.lookballs.mvvm.action.HandlerAction;
import com.lookballs.mvvm.action.KeyboardAction;
import com.lookballs.mvvm.action.ResourcesAction;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
     * 是否已经加载过
     */
    private boolean isLoading = false;

    /**
     * 是否开启懒加载，默认开启
     */
    protected boolean isLazyLoad() {
        return true;
    }

    /**
     * activity对象
     */
    protected FragmentActivity activity = null;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = requireActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView != null) {
            return rootView;
        }
        isLoading = false;
        initContentView(inflater, container);
        initView();
        initOther();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!isLazyLoad()) {
            initData();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isLazyLoad()) {
            if (!isLoading) {
                isLoading = true;
                initData();
                onFragmentResume(true);
                return;
            }
            onFragmentResume(false);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isLoading = false;
        rootView = null;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    /**
     * Fragment 可见回调
     *
     * @param firstLoad 是否首次加载
     */
    protected void onFragmentResume(boolean firstLoad) {

    }

    /**
     * 这个 Fragment 是否已经加载过了
     */
    public boolean isLoading() {
        return isLoading;
    }

    protected View getRootView() {
        return rootView;
    }

    protected void setRootView(View view) {
        this.rootView = view;
    }

    protected void initContentView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        rootView = inflater.inflate(getLayoutId(), container, false);
    }

    private void initOther() {
        if (isRegisterEventBus()) {
            if (!EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().register(this);
            }
        }
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    public Bundle getBundle() {
        return getArguments();
    }

    @Override
    public void onClick(View view) {

    }

    protected boolean isRegisterEventBus() {
        return false;
    }

    protected void receiveEvent(BaseEvent event) {

    }

    protected void receiveStickyEvent(BaseEvent event) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBus(BaseEvent event) {
        if (event != null) {
            receiveEvent(event);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onStickyEventBus(BaseEvent event) {
        if (event != null) {
            receiveStickyEvent(event);
            EventBus.getDefault().removeStickyEvent(event);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isRegisterEventBus()) {
            if (EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().unregister(this);
            }
        }
        removeCallbacks();
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
