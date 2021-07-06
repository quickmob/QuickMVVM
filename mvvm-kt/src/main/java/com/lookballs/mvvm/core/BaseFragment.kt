package com.lookballs.mvvm.core

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import com.lookballs.mvvm.BaseEvent
import com.lookballs.mvvm.action.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * 创建人：lucas
 * 创建时间：2021/5/15 11:06
 * 类描述：Fragment基类(可以懒加载)
 */
abstract class BaseFragment : Fragment(), HandlerAction, ClickAction, KeyboardAction, ResourcesAction, BundleAction {
    /**
     * 缓存视图，如果视图已经创建，则不再初始化视图
     */
    private var rootView: View? = null

    /**
     * activity对象
     */
    private lateinit var `$activity`: FragmentActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        `$activity` = requireActivity()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView != null) {
            return rootView
        }
        isLoading = false
        initContentView(inflater, container)
        initView()
        initOther()
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (!isLazyLoad()) {
            initData()
        }
    }

    override fun onResume() {
        super.onResume()
        if (isLazyLoad()) {
            if (!isLoading) {
                isLoading = true
                initData()
                onFragmentResume(true)
                return
            }
            onFragmentResume(false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isLoading = false
        rootView = null
    }

    override fun onDetach() {
        super.onDetach()
    }

    /**
     * Fragment 可见回调
     *
     * @param firstLoad 是否首次加载
     */
    protected open fun onFragmentResume(firstLoad: Boolean) {

    }

    /**
     * 是否开启懒加载，默认开启
     */
    protected open fun isLazyLoad(): Boolean {
        return true
    }

    /**
     * 这个 Fragment 是否已经加载过了
     */
    protected var isLoading = false
        private set

    protected open fun getRootView(): View? {
        return rootView
    }

    protected open fun setRootView(view: View) {
        rootView = view
    }

    protected open fun initContentView(inflater: LayoutInflater, container: ViewGroup?) {
        rootView = inflater.inflate(layoutId, container, false)
    }

    private fun initOther() {
        if (isRegisterEventBus()) {
            if (!EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().register(this)
            }
        }
    }

    protected abstract val layoutId: Int
    protected abstract fun initView()
    protected abstract fun initData()

    override val `$context`: Context
        get() = `$activity`

    override val bundle: Bundle?
        get() = arguments

    override fun onClick(view: View) {}

    open fun getAct(): FragmentActivity {
        return `$activity`
    }

    open fun isRegisterEventBus(): Boolean {
        return true
    }

    open fun receiveEvent(event: BaseEvent<*>?) {}

    open fun receiveStickyEvent(event: BaseEvent<*>?) {}

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventBus(event: BaseEvent<*>?) {
        event?.let { receiveEvent(it) }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun onStickyEventBus(event: BaseEvent<*>?) {
        event?.let {
            receiveStickyEvent(it)
            EventBus.getDefault().removeStickyEvent(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isRegisterEventBus()) {
            if (EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().unregister(this)
            }
        }
        removeCallbacks()
    }

    /**
     * 根据资源 id 获取一个 View 对象
     */
    override fun <V : View> `$findViewById`(id: Int): V {
        return rootView!!.findViewById(id)
    }

    /**
     * 销毁当前 Fragment 所在的 Activity
     */
    open fun finish() {
        if (`$activity` == null || `$activity`!!.isFinishing || `$activity`!!.isDestroyed) {
            return
        }
        `$activity`!!.finish()
    }

    /**
     * startActivityForResult 方法优化
     */
    open fun openActivityForResult(clazz: Class<out Activity>, callback: BaseActivity.OnActivityCallback?) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).openActivityForResult(clazz, null, callback)
        }
    }

    open fun openActivityForResult(clazz: Class<out Activity>, options: Bundle?, callback: BaseActivity.OnActivityCallback?) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).openActivityForResult(clazz, options, callback)
        }
    }

    open fun openActivityForResult(intent: Intent, callback: BaseActivity.OnActivityCallback?) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).openActivityForResult(intent, null, callback)
        }
    }

    open fun openActivityForResult(intent: Intent, options: Bundle?, callback: BaseActivity.OnActivityCallback?) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).openActivityForResult(intent, options, callback)
        }
    }

    open fun openActivityForResult(intent: Intent, options: Bundle?, requestCode: Int, callback: BaseActivity.OnActivityCallback?) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).openActivityForResult(intent!!, options, requestCode, callback)
        }
    }

    /**
     * Fragment 按键事件派发
     */
    open fun dispatchKeyEvent(event: KeyEvent): Boolean {
        val fragments = childFragmentManager.fragments
        for (fragment in fragments) {
            // 这个子 Fragment 必须是 BaseFragment 的子类，并且处于可见状态
            if (fragment !is BaseFragment ||
                    fragment.getLifecycle().currentState != Lifecycle.State.RESUMED) {
                continue
            }
            // 将按键事件派发给子 Fragment 进行处理
            if (fragment.dispatchKeyEvent(event)) {
                // 如果子 Fragment 拦截了这个事件，那么就不交给父 Fragment 处理
                return true
            }
        }
        return when (event.action) {
            KeyEvent.ACTION_DOWN -> onKeyDown(event.keyCode, event)
            KeyEvent.ACTION_UP -> onKeyUp(event.keyCode, event)
            else -> false
        }
    }

    /**
     * 按键按下事件回调
     */
    open fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        // 默认不拦截按键事件
        return false
    }

    /**
     * 按键抬起事件回调
     */
    open fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        // 默认不拦截按键事件
        return false
    }
}