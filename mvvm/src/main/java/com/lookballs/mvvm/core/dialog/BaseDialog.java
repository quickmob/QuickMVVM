package com.lookballs.mvvm.core.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.annotation.FloatRange;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.lookballs.mvvm.R;
import com.lookballs.mvvm.impl.lifecycle.ILifecycleObserver;

/**
 * 创建人：lucas
 * 创建时间：2021/5/15 11:06
 * 类描述：Dialog基类
 */
public abstract class BaseDialog extends AppCompatDialog implements ILifecycleObserver {
    /**
     * 缓存视图，如果视图已经创建，则不再初始化视图
     */
    private View rootView = null;

    /**
     * activity对象
     */
    private FragmentActivity activity;

    /**
     * fragment对象
     */
    @Nullable
    private Fragment fragment;

    /**
     * 子类可以实现的自定义方法
     */
    public void initContentView() {
        // 这里解释一下，为什么要传 new FrameLayout，因为如果不传的话，XML 的根布局获取到的 LayoutParams 对象会为空，也就会导致宽高参数解析不出来
        rootView = LayoutInflater.from(activity).inflate(getLayoutId(), new FrameLayout(activity), false);
        setContentView(rootView);
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    public BaseDialog(FragmentActivity activity) {
        this(activity, null, R.style.BaseDialogTheme);
    }

    public BaseDialog(FragmentActivity activity, @StyleRes int themeResId) {
        this(activity, null, themeResId);
    }

    public BaseDialog(FragmentActivity activity, @Nullable Fragment fragment) {
        this(activity, fragment, R.style.BaseDialogTheme);
    }

    public BaseDialog(FragmentActivity activity, @Nullable Fragment fragment, @StyleRes int themeResId) {
        super(activity, themeResId);
        this.activity = activity;
        this.fragment = fragment;
        if (activity != null) {
            activity.getLifecycle().addObserver(this);//添加LifecycleObserver
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentView();
        initView();

        initOther();
        initData();
    }

    private void initOther() {
        setCancelable(setCancelable());
        setCanceledOnTouchOutside(setCanceledOnTouchOutside());
        getRootView().post(new Runnable() {
            @Override
            public void run() {
                setWindowWidth(getRootView().getWidth());
                setWindowHeight(getRootView().getHeight());
            }
        });
    }

    public View getRootView() {
        return rootView;
    }

    public void setRootView(View view) {
        this.rootView = view;
    }

    public FragmentActivity getAct() {
        return activity;
    }

    public Fragment getFg() {
        return fragment;
    }

    /**
     * 1、setCancelable设置为true，setCanceledOnTouchOutside设置为true：点击空白处消失，按返回键消失
     * 2、setCancelable设置为true，setCanceledOnTouchOutside设置为false：点击空白处不消失，按返回键消失
     * 3、setCancelable设置为false，setCanceledOnTouchOutside设置为true：点击空白处消失，按返回键消失
     * 4、setCancelable设置为false，setCanceledOnTouchOutside设置为false：点击空白处不消失，按返回键不消失
     */
    public boolean setCancelable() {
        return true;
    }

    public boolean setCanceledOnTouchOutside() {
        return true;
    }

    /**
     * 设置宽度
     */
    public void setWindowWidth(int width) {
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = width;
            window.setAttributes(params);
        }
    }

    /**
     * 设置高度
     */
    public void setWindowHeight(int height) {
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.height = height;
            window.setAttributes(params);
        }
    }

    /**
     * 设置水平偏移
     */
    public void setXOffset(int offset) {
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.x = offset;
            window.setAttributes(params);
        }
    }

    /**
     * 设置垂直偏移
     */
    public void setYOffset(int offset) {
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.y = offset;
            window.setAttributes(params);
        }
    }

    /**
     * 设置 Dialog 重心
     */
    public void setGravity(int gravity) {
        Window window = getWindow();
        if (window != null) {
            window.setGravity(gravity);
        }
    }

    /**
     * 设置 Dialog 的动画
     */
    public void setWindowAnimations(@StyleRes int id) {
        Window window = getWindow();
        if (window != null) {
            window.setWindowAnimations(id);
        }
    }

    /**
     * 设置背景遮盖层开关
     */
    public void setBackgroundDimEnabled(boolean enabled) {
        Window window = getWindow();
        if (window != null) {
            if (enabled) {
                window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            } else {
                window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            }
        }
    }

    /**
     * 设置背景遮盖层的透明度（前提条件是背景遮盖层开关必须是为开启状态）
     */
    public void setBackgroundDimAmount(@FloatRange(from = 0.0, to = 1.0) float dimAmount) {
        Window window = getWindow();
        if (window != null) {
            window.setDimAmount(dimAmount);
        }
    }

    @Override
    public void show() {
        if (activity != null && activity.isFinishing()) {
            return;
        }
        super.show();
    }

    @Override
    public void onLifecycleChanged(LifecycleOwner owner, Lifecycle.Event event) {
        if (event.equals(Lifecycle.Event.ON_DESTROY)) {
            if (isShowing()) {
                dismiss();
                owner.getLifecycle().removeObserver(this);
            }
        }
    }
}
