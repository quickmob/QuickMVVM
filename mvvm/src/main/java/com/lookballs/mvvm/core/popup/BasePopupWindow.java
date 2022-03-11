package com.lookballs.mvvm.core.popup;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import androidx.annotation.FloatRange;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.core.widget.PopupWindowCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.lookballs.mvvm.impl.lifecycle.ILifecycleObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建人：lucas
 * 创建时间：2021/5/15 11:06
 * 类描述：PopupWindow基类
 */
public abstract class BasePopupWindow extends PopupWindow implements ILifecycleObserver, PopupWindow.OnDismissListener {
    /**
     * 缓存视图，如果视图已经创建，则不再初始化视图
     */
    private View rootView = null;

    /**
     * activity对象
     */
    private FragmentActivity activity;

    /**
     * 显示监听
     */
    private List<OnShowListener> mShowListeners;

    /**
     * 关闭监听
     */
    private List<OnDismissListener> mDismissListeners;

    /**
     * 窗口透明度显示监听
     */
    private PopupBackground mPopupBackground;

    /**
     * 子类可以实现的自定义方法
     */
    public void initContentView() {
        rootView = LayoutInflater.from(activity).inflate(getLayoutId(), null, false);
        setContentView(rootView);
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    public BasePopupWindow(FragmentActivity activity) {
        super(activity);
        if (activity != null) {
            this.activity = activity;
            activity.getLifecycle().addObserver(this);//添加LifecycleObserver

            initContentView();
            initView();

            initOther();
            initData();
        }
    }

    private void initOther() {
        setWidth(setWindowWidth());
        setHeight(setWindowHeight());
        //设置透明背景
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setOutsideTouchable(setWindowOutsideTouchable());
        setFocusable(setWindowFocusable());
        setTouchable(setWindowTouchable());
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

    /**
     * 设置宽度
     */
    public int setWindowWidth() {
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }

    /**
     * 设置高度
     */
    public int setWindowHeight() {
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }

    /**
     * 1、setOutsideTouchable设置为true，setFocusable设置为true，setTouchable设置为true：点击窗口外部可关闭，按返回键可关闭，不响应外部点击事件
     * 2、setOutsideTouchable设置为true，setFocusable设置为false，setTouchable设置为true：点击窗口外部可关闭，按返回键直接退出页面，响应外部点击事件
     * 3、setOutsideTouchable设置为true，setFocusable设置为true，setTouchable设置为false：点击窗口外部可关闭，按返回键可关闭，响应外部点击事件
     * 4、setOutsideTouchable设置为true，setFocusable设置为false，setTouchable设置为false：点击窗口外部可关闭，按返回键直接退出页面，响应外部点击事件
     * 5、setOutsideTouchable设置为false，setFocusable设置为true，setTouchable设置为true：点击窗口外部可关闭，按返回键可关闭，不响应外部点击事件
     * 6、setOutsideTouchable设置为false，setFocusable设置为false，setTouchable设置为true：点击窗口外部不可关闭，按返回键直接退出页面，响应外部点击事件
     * 7、setOutsideTouchable设置为false，setFocusable设置为true，setTouchable设置为false：点击窗口外部不可关闭，按返回键可关闭，响应外部点击事件
     * 8、setOutsideTouchable设置为false，setFocusable设置为false，setTouchable设置为false：点击窗口外部不可关闭，按返回键直接退出页面，响应外部点击事件
     */
    public boolean setWindowOutsideTouchable() {
        return true;
    }

    public boolean setWindowFocusable() {
        return true;
    }

    public boolean setWindowTouchable() {
        return true;
    }

    /**
     * 添加一个显示监听器
     *
     * @param listener 监听器对象
     */
    public void addOnShowListener(@Nullable OnShowListener listener) {
        if (mShowListeners == null) {
            mShowListeners = new ArrayList<>();
        }
        mShowListeners.add(listener);
    }

    /**
     * 添加一个销毁监听器
     *
     * @param listener 监听器对象
     */
    public void addOnDismissListener(@Nullable OnDismissListener listener) {
        if (mDismissListeners == null) {
            mDismissListeners = new ArrayList<>();
            super.setOnDismissListener(this);
        }
        mDismissListeners.add(listener);
    }

    /**
     * 移除一个显示监听器
     *
     * @param listener 监听器对象
     */
    public void removeOnShowListener(@Nullable OnShowListener listener) {
        if (mShowListeners != null) {
            mShowListeners.remove(listener);
        }
    }

    /**
     * 移除一个销毁监听器
     *
     * @param listener 监听器对象
     */
    public void removeOnDismissListener(@Nullable OnDismissListener listener) {
        if (mDismissListeners != null) {
            mDismissListeners.remove(listener);
        }
    }

    /**
     * 设置 Dialog 的动画
     */
    public void setWindowAnimationStyle(@StyleRes int id) {
        setAnimationStyle(id);
    }

    /**
     * 设置背景遮盖层的透明度
     */
    public void setWindowBackgroundAlpha(@FloatRange(from = 0.0, to = 1.0) float dimAmount) {
        float alpha = 1 - dimAmount;
        if (isShowing()) {
            setActivityAlpha(alpha);
        }
        if (mPopupBackground == null && alpha != 1) {
            mPopupBackground = new PopupBackground();
            addOnShowListener(mPopupBackground);
            addOnDismissListener(mPopupBackground);
        }
        if (mPopupBackground != null) {
            mPopupBackground.setAlpha(alpha);
        }
    }

    /**
     * 设置 Activity 窗口透明度
     */
    private void setActivityAlpha(float alpha) {
        if (activity == null) {
            return;
        }

        WindowManager.LayoutParams params = activity.getWindow().getAttributes();

        final ValueAnimator animator = ValueAnimator.ofFloat(params.alpha, alpha);
        animator.setDuration(300);
        animator.addUpdateListener(animation -> {
            float value = (float) animation.getAnimatedValue();
            if (value != params.alpha) {
                params.alpha = value;
                activity.getWindow().setAttributes(params);
            }
        });
        animator.start();
    }

    @Override
    public void setWindowLayoutType(int type) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            super.setWindowLayoutType(type);
        } else {
            PopupWindowCompat.setWindowLayoutType(this, type);
        }
    }

    @Override
    public void setOverlapAnchor(boolean overlapAnchor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            super.setOverlapAnchor(overlapAnchor);
        } else {
            PopupWindowCompat.setOverlapAnchor(this, overlapAnchor);
        }
    }

    @Override
    public void showAsDropDown(View anchor) {
        showAsDropDown(anchor, 0, 0);
    }

    @Override
    public void showAsDropDown(View anchor, int xOff, int yOff) {
        showAsDropDown(anchor, xOff, yOff, Gravity.TOP | Gravity.START);
    }

    @Override
    public void showAsDropDown(View anchor, int xOff, int yOff, int gravity) {
        if (isShowing() || getContentView() == null) {
            return;
        }
        if (mShowListeners != null) {
            for (OnShowListener listener : mShowListeners) {
                listener.onShow(this);
            }
        }
        super.showAsDropDown(anchor, xOff, yOff, gravity);
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        if (isShowing() || getContentView() == null) {
            return;
        }
        if (mShowListeners != null) {
            for (OnShowListener listener : mShowListeners) {
                listener.onShow(this);
            }
        }
        super.showAtLocation(parent, gravity, x, y);
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

    @Override
    public void dismiss() {
        super.dismiss();
    }

    @Override
    public void onDismiss() {
        if (mDismissListeners != null) {
            for (OnDismissListener listener : mDismissListeners) {
                listener.onDismiss(this);
            }
        }
    }

    /**
     * PopupWindow 背景遮盖层实现类
     */
    private static class PopupBackground implements OnShowListener, OnDismissListener {

        private float mAlpha;

        private void setAlpha(float alpha) {
            mAlpha = alpha;
        }

        @Override
        public void onShow(BasePopupWindow popupWindow) {
            popupWindow.setActivityAlpha(mAlpha);
        }

        @Override
        public void onDismiss(BasePopupWindow popupWindow) {
            popupWindow.setActivityAlpha(1);
        }
    }

    /**
     * 显示监听器
     */
    public interface OnShowListener {

        /**
         * PopupWindow 显示了
         */
        void onShow(BasePopupWindow popupWindow);
    }

    /**
     * 销毁监听器
     */
    public interface OnDismissListener {

        /**
         * PopupWindow 销毁了
         */
        void onDismiss(BasePopupWindow popupWindow);
    }
}
