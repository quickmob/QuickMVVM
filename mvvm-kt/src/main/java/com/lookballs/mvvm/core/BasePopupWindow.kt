package com.lookballs.mvvm.core

import android.animation.ValueAnimator
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.PopupWindow
import androidx.annotation.FloatRange
import androidx.annotation.StyleRes
import androidx.core.widget.PopupWindowCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

/**
 * 创建人：lucas
 * 创建时间：2021/5/15 11:06
 * 类描述：PopupWindow基类
 */
abstract class BasePopupWindow(activity: FragmentActivity) : PopupWindow(activity), ILifecycleObserver, PopupWindow.OnDismissListener {
    /**
     * 缓存视图，如果视图已经创建，则不再初始化视图
     */
    private var rootView: View? = null

    /**
     * activity对象
     */
    private var activity: FragmentActivity = activity

    /**
     * 显示监听
     */
    private val mShowListeners: MutableList<OnShowListener?> by lazy {
        mutableListOf<OnShowListener?>()
    }

    /**
     * 关闭监听
     */
    private val mDismissListeners: MutableList<OnDismissListener?> by lazy {
        mutableListOf<OnDismissListener?>()
    }

    /**
     * 窗口透明度显示监听
     */
    private var mPopupBackground: PopupBackground? = null

    private fun initConfig() {
        width = setWindowWidth()
        height = setWindowHeight()
        //设置透明背景
        setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        isOutsideTouchable = setWindowOutsideTouchable()
        isFocusable = setWindowFocusable()
        isTouchable = setWindowTouchable()
    }

    protected open fun getRootView(): View? {
        return rootView
    }

    protected open fun setRootView(view: View) {
        rootView = view
    }

    protected open fun initContentView() {
        rootView = LayoutInflater.from(activity).inflate(layoutId, null, false)
        contentView = rootView
    }

    protected abstract val layoutId: Int
    protected abstract fun initView()
    protected abstract fun initData()

    open fun getAct(): FragmentActivity {
        return activity
    }

    /**
     * 设置宽度
     */
    open fun setWindowWidth(): Int {
        return WindowManager.LayoutParams.WRAP_CONTENT
    }

    /**
     * 设置高度
     */
    open fun setWindowHeight(): Int {
        return WindowManager.LayoutParams.WRAP_CONTENT
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
    open fun setWindowOutsideTouchable(): Boolean {
        return true
    }

    open fun setWindowFocusable(): Boolean {
        return true
    }

    open fun setWindowTouchable(): Boolean {
        return true
    }

    /**
     * 添加一个显示监听器
     *
     * @param listener 监听器对象
     */
    open fun addOnShowListener(listener: OnShowListener?) {
        mShowListeners.add(listener)
    }

    /**
     * 添加一个销毁监听器
     *
     * @param listener 监听器对象
     */
    open fun addOnDismissListener(listener: OnDismissListener?) {
        super.setOnDismissListener(this)
        mDismissListeners!!.add(listener)
    }

    /**
     * 移除一个显示监听器
     *
     * @param listener 监听器对象
     */
    open fun removeOnShowListener(listener: OnShowListener?) {
        if (mShowListeners != null) {
            mShowListeners!!.remove(listener)
        }
    }

    /**
     * 移除一个销毁监听器
     *
     * @param listener 监听器对象
     */
    open fun removeOnDismissListener(listener: OnDismissListener?) {
        if (mDismissListeners != null) {
            mDismissListeners!!.remove(listener)
        }
    }

    /**
     * 设置 Dialog 的动画
     */
    open fun setWindowAnimationStyle(@StyleRes id: Int) {
        animationStyle = id
    }

    /**
     * 设置背景遮盖层的透明度
     */
    open fun setWindowBackgroundAlpha(@FloatRange(from = 0.0, to = 1.0) dimAmount: Float) {
        val alpha = 1 - dimAmount
        if (isShowing) {
            setActivityAlpha(alpha)
        }
        if (mPopupBackground == null && alpha != 1f) {
            mPopupBackground = PopupBackground()
            addOnShowListener(mPopupBackground)
            addOnDismissListener(mPopupBackground)
        }
        mPopupBackground?.setAlpha(alpha)
    }

    /**
     * 设置 Activity 窗口透明度
     */
    private fun setActivityAlpha(alpha: Float) {
        activity?.let {
            val params = it.window.attributes
            val animator = ValueAnimator.ofFloat(params.alpha, alpha)
            animator.duration = 300
            animator.addUpdateListener { animation: ValueAnimator ->
                val value = animation.animatedValue as Float
                if (value != params.alpha) {
                    params.alpha = value
                    it.window.attributes = params
                }
            }
            animator.start()
        }
    }

    override fun setWindowLayoutType(type: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            super.setWindowLayoutType(type)
        } else {
            PopupWindowCompat.setWindowLayoutType(this, type)
        }
    }

    override fun setOverlapAnchor(overlapAnchor: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            super.setOverlapAnchor(overlapAnchor)
        } else {
            PopupWindowCompat.setOverlapAnchor(this, overlapAnchor)
        }
    }

    override fun showAsDropDown(anchor: View) {
        showAsDropDown(anchor, 0, 0)
    }

    override fun showAsDropDown(anchor: View, xOff: Int, yOff: Int) {
        showAsDropDown(anchor, xOff, yOff, Gravity.TOP or Gravity.START)
    }

    override fun showAsDropDown(anchor: View, xOff: Int, yOff: Int, gravity: Int) {
        if (isShowing || contentView == null) {
            return
        }
        for (listener in mShowListeners) {
            listener?.onShow(this)
        }
        super.showAsDropDown(anchor, xOff, yOff, gravity)
    }

    override fun showAtLocation(parent: View, gravity: Int, x: Int, y: Int) {
        if (isShowing || contentView == null) {
            return
        }
        for (listener in mShowListeners) {
            listener?.onShow(this)
        }
        super.showAtLocation(parent, gravity, x, y)
    }

    override fun onLifecycleChanged(owner: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            if (isShowing) {
                dismiss()
                owner.lifecycle.removeObserver(this)
            }
        }
    }

    override fun dismiss() {
        super.dismiss()
    }

    override fun onDismiss() {
        for (listener in mDismissListeners) {
            listener?.onDismiss(this)
        }
    }

    /**
     * PopupWindow 背景遮盖层实现类
     */
    private class PopupBackground : OnShowListener, OnDismissListener {
        private var mAlpha = 0f
        fun setAlpha(alpha: Float) {
            mAlpha = alpha
        }

        override fun onShow(popupWindow: BasePopupWindow) {
            popupWindow.setActivityAlpha(mAlpha)
        }

        override fun onDismiss(popupWindow: BasePopupWindow) {
            popupWindow.setActivityAlpha(1f)
        }
    }

    /**
     * 显示监听器
     */
    interface OnShowListener {
        /**
         * PopupWindow 显示了
         */
        fun onShow(popupWindow: BasePopupWindow)
    }

    /**
     * 销毁监听器
     */
    interface OnDismissListener {
        /**
         * PopupWindow 销毁了
         */
        fun onDismiss(popupWindow: BasePopupWindow)
    }

    init {
        activity.lifecycle.addObserver(this) //添加LifecycleObserver
        initContentView()
        initView()
        initConfig()
        initData()
    }
}