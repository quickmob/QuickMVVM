package com.lookballs.mvvm.core.binding

import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.lookballs.mvvm.core.BasePopupWindow

/**
 * 创建人：lucas
 * 创建时间：2021/5/15 11:06
 * 类描述：基于ViewDataBinding的PopupWindow基类
 */
abstract class BaseBindingPopupWindow<DB : ViewDataBinding>(activity: FragmentActivity) : BasePopupWindow(activity) {
    private lateinit var dataBinding: DB

    override fun initContentView() {
        injectDataBinding()
    }

    private fun injectDataBinding() {
        dataBinding = DataBindingUtil.inflate(LayoutInflater.from(getAct()), layoutId, null, false)
        setRootView(dataBinding.root)
        contentView = getRootView()
    }

    open fun dataBinding(): DB {
        return dataBinding
    }

    override fun onLifecycleChanged(owner: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            if (isShowing) {
                dataBinding.unbind()
            }
        }
        super.onLifecycleChanged(owner, event)
    }
}