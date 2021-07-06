package com.lookballs.mvvm.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.annotation.FloatRange
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.lookballs.mvvm.R

/**
 * 创建人：lucas
 * 创建时间：2021/5/15 11:06
 * 类描述：Dialog基类
 */
abstract class BaseDialog @JvmOverloads constructor(activity: FragmentActivity, fragment: Fragment?, @StyleRes themeResId: Int = R.style.BaseDialogTheme) : AppCompatDialog(activity, themeResId), ILifecycleObserver {
    /**
     * 缓存视图，如果视图已经创建，则不再初始化视图
     */
    private var rootView: View? = null

    /**
     * activity对象
     */
    private var activity: FragmentActivity = activity

    /**
     * fragment对象
     */
    private var fragment: Fragment? = fragment

    init {
        this.activity = activity
        this.fragment = fragment
        activity.lifecycle.addObserver(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContentView()
        initView()
        initConfig()
        initData()
    }

    private fun initConfig() {
        setCancelable(setCancelable())
        setCanceledOnTouchOutside(setCanceledOnTouchOutside())
        rootView?.let {
            it.post {
                setWindowWidth(it.width)
                setWindowHeight(it.height)
            }
        }
    }

    protected open fun getRootView(): View? {
        return rootView
    }

    protected open fun setRootView(view: View) {
        rootView = view
    }

    protected open fun initContentView() {
        // 这里解释一下，为什么要传 new FrameLayout，因为如果不传的话，XML 的根布局获取到的 LayoutParams 对象会为空，也就会导致宽高参数解析不出来
        rootView = LayoutInflater.from(activity).inflate(layoutId, FrameLayout(activity), false)
        rootView?.let {
            setContentView(it)
        }
    }

    protected abstract val layoutId: Int
    protected abstract fun initView()
    protected abstract fun initData()

    open fun getAct(): FragmentActivity {
        return activity
    }

    open fun getFg(): Fragment? {
        return fragment
    }

    /**
     * 1、setCancelable设置为true，setCanceledOnTouchOutside设置为true：点击空白处消失，按返回键消失
     * 2、setCancelable设置为true，setCanceledOnTouchOutside设置为false：点击空白处不消失，按返回键消失
     * 3、setCancelable设置为false，setCanceledOnTouchOutside设置为true：点击空白处消失，按返回键消失
     * 4、setCancelable设置为false，setCanceledOnTouchOutside设置为false：点击空白处不消失，按返回键不消失
     */
    open fun setCancelable(): Boolean {
        return true
    }

    open fun setCanceledOnTouchOutside(): Boolean {
        return true
    }

    /**
     * 设置宽度
     */
    open fun setWindowWidth(width: Int) {
        window?.let {
            val params = it.attributes
            params.width = width
            it.attributes = params
        }
    }

    /**
     * 设置高度
     */
    open fun setWindowHeight(height: Int) {
        window?.let {
            val params = it.attributes
            params.height = height
            it.attributes = params
        }
    }

    /**
     * 设置水平偏移
     */
    open fun setXOffset(offset: Int) {
        window?.let {
            val params = it.attributes
            params.x = offset
            it.attributes = params
        }
    }

    /**
     * 设置垂直偏移
     */
    open fun setYOffset(offset: Int) {
        window?.let {
            val params = it.attributes
            params.y = offset
            it.attributes = params
        }
    }

    /**
     * 设置 Dialog 重心
     */
    open fun setGravity(gravity: Int) {
        window?.setGravity(gravity)
    }

    /**
     * 设置 Dialog 的动画
     */
    open fun setWindowAnimations(@StyleRes id: Int) {
        window?.setWindowAnimations(id)
    }

    /**
     * 设置背景遮盖层开关
     */
    open fun setBackgroundDimEnabled(enabled: Boolean) {
        window?.let {
            if (enabled) {
                it.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            } else {
                it.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            }
        }
    }

    /**
     * 设置背景遮盖层的透明度（前提条件是背景遮盖层开关必须是为开启状态）
     */
    open fun setBackgroundDimAmount(@FloatRange(from = 0.0, to = 1.0) dimAmount: Float) {
        window?.setDimAmount(dimAmount)
    }

    override fun show() {
        if (activity != null && activity.isFinishing) {
            return
        }
        super.show()
    }

    override fun onLifecycleChanged(owner: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            if (isShowing) {
                dismiss()
                owner.lifecycle.removeObserver(this)
            }
        }
    }
}