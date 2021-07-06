package com.lookballs.mvvm.core.binding

import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.StyleRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.lookballs.mvvm.R
import com.lookballs.mvvm.core.BaseDialog

/**
 * 创建人：lucas
 * 创建时间：2021/5/15 11:06
 * 类描述：基于ViewDataBinding的Dialog基类
 */
abstract class BaseBindingDialog<DB : ViewDataBinding> @JvmOverloads constructor(activity: FragmentActivity, fragment: Fragment? = null, @StyleRes themeResId: Int = R.style.BaseDialogTheme) : BaseDialog(activity, fragment, themeResId) {
    private lateinit var dataBinding: DB

    override fun initContentView() {
        injectDataBinding()
    }

    private fun injectDataBinding() {
        // 这里解释一下，为什么要传 new FrameLayout，因为如果不传的话，XML 的根布局获取到的 LayoutParams 对象会为空，也就会导致宽高参数解析不出来
        dataBinding = DataBindingUtil.inflate(LayoutInflater.from(getAct()), layoutId, FrameLayout(getAct()), false)
        setRootView(dataBinding.root)
        setContentView(dataBinding.root)
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