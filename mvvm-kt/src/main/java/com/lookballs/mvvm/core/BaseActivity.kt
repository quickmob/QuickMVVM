package com.lookballs.mvvm.core

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.SparseArray
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.lookballs.mvvm.BaseEvent
import com.lookballs.mvvm.action.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*
import kotlin.math.pow

/**
 * 创建人：lucas
 * 创建时间：2021/5/15 11:06
 * 类描述：Activity基类
 */
abstract class BaseActivity : AppCompatActivity(), ILifecycleObserver, HandlerAction, ClickAction, KeyboardAction, ResourcesAction, BundleAction {
    /**
     * 上下文对象
     */
    private val activity: FragmentActivity = this

    /**
     * 是否销毁页面
     */
    private var isDestroyedActivity = false

    /**
     * Activity 回调集合
     */
    private val mActivityCallbacks: SparseArray<OnActivityCallback> by lazy {
        SparseArray<OnActivityCallback>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        initBefore()
        super.onCreate(savedInstanceState)
        initContentView()
        initView()
        initOther()
    }

    override fun onLifecycleChanged(owner: LifecycleOwner, event: Lifecycle.Event) {
        if (Lifecycle.Event.ON_CREATE == event) {
            //这里解决当在initData调用ViewModel后，无法及时获取LifecycleOwner问题，因为会先调用initData再回调ON_CREATE，所以这里在回调ON_CREATE时调用initData
            initData()
        }
    }

    private fun initOther() {
        lifecycle.addObserver(this)
        if (isRegisterEventBus()) {
            if (!EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().register(this)
            }
        }
        clearFragments()
        //点击外部隐藏软键盘，提升用户体验
        contentView?.setOnClickListener {
            //隐藏软键，避免内存泄漏
            hideKeyboard(currentFocus)
        }
    }

    protected open fun initBefore() {}

    protected open fun initContentView() {
        setContentView(layoutId)
    }

    protected abstract val layoutId: Int
    protected abstract fun initView()
    protected abstract fun initData()

    /**
     * 根据资源 id 获取一个 View 对象
     */
    override fun <V : View> `$findViewById`(id: Int): V {
        return findViewById<View>(id) as V
    }

    /**
     * 和setContentView对应的方法
     */
    open val contentView: ViewGroup
        get() = `$findViewById`(Window.ID_ANDROID_CONTENT)

    override fun finish() {
        //隐藏软键，避免内存泄漏
        hideKeyboard(currentFocus)
        super.finish()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        //设置为当前的 Intent，避免 Activity 被杀死后重启 Intent 还是最原先的那个
        setIntent(intent)
    }

    override val `$context`: Context
        get() = this

    override val bundle: Bundle?
        get() = intent.extras

    override fun onClick(view: View) {}

    open fun getAct(): FragmentActivity {
        return activity
    }

    open fun isRegisterEventBus(): Boolean {
        return false
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

    override fun onPause() {
        super.onPause()
        if (isFinishing) {
            destroy()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        destroy()
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        val fragments = supportFragmentManager.fragments
        for (fragment in fragments) {
            // 这个 Fragment 必须是 BaseFragment 的子类，并且处于可见状态
            if (fragment !is BaseFragment ||
                    fragment.getLifecycle().currentState != Lifecycle.State.RESUMED) {
                continue
            }
            // 将按键事件派发给 Fragment 进行处理
            if (fragment.dispatchKeyEvent(event)) {
                // 如果 Fragment 拦截了这个事件，那么就不交给 Activity 处理
                return true
            }
        }
        return super.dispatchKeyEvent(event)
    }

    /**
     * 处理因为Activity重建导致的fragment叠加问题
     */
    private fun clearFragments() {
        try {
            val fragments = supportFragmentManager.fragments
            if (fragments == null || fragments.size == 0) {
                return
            }
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            for (fragment in fragments) {
                fragmentTransaction.remove(fragment)
            }
            fragmentTransaction.commitAllowingStateLoss()
        } catch (e: Exception) {

        }
    }

    /**
     * 解决activity被finish后onDestroy()不立即执行问题
     */
    private fun destroy() {
        if (isDestroyedActivity) {
            return
        } else {
            //回收资源
            isDestroyedActivity = true
            if (isRegisterEventBus()) {
                if (EventBus.getDefault().isRegistered(this)) {
                    EventBus.getDefault().unregister(this)
                }
            }
            removeCallbacks()
        }
    }

    override fun startActivityForResult(intent: Intent, requestCode: Int, options: Bundle?) {
        //隐藏软键，避免内存泄漏
        hideKeyboard(currentFocus)
        //查看源码得知 startActivity 最终也会调用 startActivityForResult
        super.startActivityForResult(intent, requestCode, options)
    }

    /**
     * startActivityForResult方法优化
     */
    open fun openActivityForResult(clazz: Class<out Activity>, callback: OnActivityCallback?) {
        openActivityForResult(clazz, null, callback)
    }

    open fun openActivityForResult(clazz: Class<out Activity>, options: Bundle?, callback: OnActivityCallback?) {
        openActivityForResult(Intent(this, clazz), options, callback)
    }

    open fun openActivityForResult(intent: Intent, callback: OnActivityCallback?) {
        openActivityForResult(intent, null, callback)
    }

    open fun openActivityForResult(intent: Intent, options: Bundle?, callback: OnActivityCallback?) {
        //请求码必须在 2 的 16 次方以内
        val requestCode = Random().nextInt(2.0.pow(16.0).toInt())
        openActivityForResult(intent, options, requestCode, callback)
    }

    open fun openActivityForResult(intent: Intent, options: Bundle?, requestCode: Int, callback: OnActivityCallback?) {
        mActivityCallbacks.put(requestCode, callback)
        startActivityForResult(intent, requestCode, options)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        mActivityCallbacks[requestCode]?.let {
            it.onActivityResult(requestCode, resultCode, data)
            mActivityCallbacks.remove(requestCode)
            return
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    interface OnActivityCallback {
        /**
         * 结果回调
         *
         * @param requestCode 请求码
         * @param resultCode  结果码
         * @param data        数据
         */
        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    }
}