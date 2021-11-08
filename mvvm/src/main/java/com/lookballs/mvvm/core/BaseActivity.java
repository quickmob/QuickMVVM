package com.lookballs.mvvm.core;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.lookballs.mvvm.action.BundleAction;
import com.lookballs.mvvm.action.ClickAction;
import com.lookballs.mvvm.action.HandlerAction;
import com.lookballs.mvvm.action.KeyboardAction;
import com.lookballs.mvvm.action.ResourcesAction;

import java.util.List;
import java.util.Random;

/**
 * 创建人：lucas
 * 创建时间：2021/5/15 11:06
 * 类描述：Activity基类
 */
public abstract class BaseActivity extends AppCompatActivity implements ILifecycleObserver, HandlerAction, ClickAction, KeyboardAction, ResourcesAction, BundleAction {

    /**
     * 上下文对象
     */
    private FragmentActivity activity = this;

    /**
     * 是否销毁页面
     */
    private boolean isDestroyed = false;

    /**
     * 页面是否显示
     */
    private boolean isShowing = false;

    /**
     * Activity 回调集合
     */
    private SparseArray<OnActivityCallback> mActivityCallbacks;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initBefore();
        super.onCreate(savedInstanceState);
        initContentView();
        initView();
        initOther();
    }

    @Override
    public void onLifecycleChanged(LifecycleOwner owner, Lifecycle.Event event) {
        if (Lifecycle.Event.ON_CREATE.equals(event)) {
            //这里解决当在initData调用ViewModel后，无法及时获取LifecycleOwner问题，因为会先调用initData再回调ON_CREATE，所以这里在回调ON_CREATE时调用initData
            initData();
        }
    }

    private void initOther() {
        getLifecycle().addObserver(this);
        clearFragments();
        //点击外部隐藏软键盘，提升用户体验
        getContentView().setOnClickListener(v -> {
            //隐藏软键，避免内存泄漏
            hideKeyboard(getCurrentFocus());
        });
    }

    public void initBefore() {

    }

    public void initContentView() {
        setContentView(getLayoutId());
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    public FragmentActivity getAct() {
        return activity;
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
    public <T extends View> T findViewById(int id) {
        return super.findViewById(id);
    }

    /**
     * 和setContentView对应的方法
     */
    public ViewGroup getContentView() {
        return findViewById(Window.ID_ANDROID_CONTENT);
    }

    @Override
    public void finish() {
        //隐藏软键，避免内存泄漏
        hideKeyboard(getCurrentFocus());
        super.finish();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        //设置为当前的 Intent，避免 Activity 被杀死后重启 Intent 还是最原先的那个
        setIntent(intent);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Nullable
    @Override
    public Bundle getBundle() {
        return getIntent().getExtras();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        isShowing = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isShowing = false;
        if (isFinishing()) {
            destroy();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isShowing = false;
        destroy();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            // 这个 Fragment 必须是 BaseFragment 的子类，并且处于可见状态
            if (!(fragment instanceof BaseFragment) ||
                    fragment.getLifecycle().getCurrentState() != Lifecycle.State.RESUMED) {
                continue;
            }
            // 将按键事件派发给 Fragment 进行处理
            if (((BaseFragment) fragment).dispatchKeyEvent(event)) {
                // 如果 Fragment 拦截了这个事件，那么就不交给 Activity 处理
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    /**
     * 处理因为Activity重建导致的fragment叠加问题
     */
    private void clearFragments() {
        try {
            List<Fragment> fragments = getSupportFragmentManager().getFragments();
            if (fragments == null || fragments.size() == 0) {
                return;
            }
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            for (Fragment fragment : fragments) {
                fragmentTransaction.remove(fragment);
            }
            fragmentTransaction.commitAllowingStateLoss();
        } catch (Exception e) {

        }
    }

    /**
     * 解决activity被finish后onDestroy()不立即执行问题
     */
    private void destroy() {
        if (isDestroyed) {
            return;
        } else {
            //回收资源
            isDestroyed = true;
            removeCallbacks();
        }
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        //隐藏软键，避免内存泄漏
        hideKeyboard(getCurrentFocus());
        //查看源码得知 startActivity 最终也会调用 startActivityForResult
        super.startActivityForResult(intent, requestCode, options);
    }

    /**
     * startActivityForResult方法优化
     */
    public void openActivityForResult(Class<? extends Activity> clazz, OnActivityCallback callback) {
        openActivityForResult(clazz, null, callback);
    }

    public void openActivityForResult(Class<? extends Activity> clazz, @Nullable Bundle options, OnActivityCallback callback) {
        openActivityForResult(new Intent(this, clazz), options, callback);
    }

    public void openActivityForResult(Intent intent, OnActivityCallback callback) {
        openActivityForResult(intent, null, callback);
    }

    public void openActivityForResult(Intent intent, @Nullable Bundle options, OnActivityCallback callback) {
        //请求码必须在 2 的 16 次方以内
        int requestCode = new Random().nextInt((int) Math.pow(2, 16));
        openActivityForResult(intent, options, requestCode, callback);
    }

    public void openActivityForResult(Intent intent, @Nullable Bundle options, int requestCode, OnActivityCallback callback) {
        if (mActivityCallbacks == null) {
            mActivityCallbacks = new SparseArray<>(1);
        }
        mActivityCallbacks.put(requestCode, callback);
        startActivityForResult(intent, requestCode, options);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        OnActivityCallback callback;
        if (mActivityCallbacks != null && (callback = mActivityCallbacks.get(requestCode)) != null) {
            callback.onActivityResult(requestCode, resultCode, data);
            mActivityCallbacks.remove(requestCode);
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public interface OnActivityCallback {
        /**
         * 结果回调
         *
         * @param requestCode 请求码
         * @param resultCode  结果码
         * @param data        数据
         */
        void onActivityResult(int requestCode, int resultCode, @Nullable Intent data);
    }
}
