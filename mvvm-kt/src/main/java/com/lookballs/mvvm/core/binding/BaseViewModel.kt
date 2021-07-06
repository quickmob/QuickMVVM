package com.lookballs.mvvm.core.binding

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lookballs.mvvm.core.ILifecycleObserver

/**
 * 创建人：lucas
 * 创建时间：2021/5/15 11:06
 * 类描述：ViewModel基类
 */
open class BaseViewModel : ViewModel(), ILifecycleObserver {
    private val mDialogData by lazy {
        MutableLiveData<Any>()
    }
    private val mLiveData by lazy {
        MutableLiveData<Any>()
    }
    private var mLifecycleOwner: LifecycleOwner? = null

    override fun onLifecycleChanged(owner: LifecycleOwner, event: Lifecycle.Event) {
        mLifecycleOwner = owner
    }

    open fun getLifecycleOwner(): LifecycleOwner? {
        return mLifecycleOwner
    }

    open fun getDialogData(): MutableLiveData<Any> {
        return mDialogData
    }

    open fun getLiveData(): MutableLiveData<Any> {
        return mLiveData
    }
}