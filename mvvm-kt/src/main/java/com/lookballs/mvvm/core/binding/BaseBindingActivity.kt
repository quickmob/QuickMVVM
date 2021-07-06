package com.lookballs.mvvm.core.binding

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lookballs.mvvm.core.BaseActivity

/**
 * 创建人：lucas
 * 创建时间：2021/5/15 11:06
 * 类描述：基于MVVM模式的Activity基类
 */
abstract class BaseBindingActivity<VM : BaseViewModel, DB : ViewDataBinding> : BaseActivity(), Observer<Any?> {
    private lateinit var dataBinding: DB
    private var viewModel: VM? = null

    override fun initContentView() {
        injectDataBinding()
        injectViewModel()
    }

    private fun injectDataBinding() {
        dataBinding = DataBindingUtil.setContentView(getAct(), layoutId)
        dataBinding.lifecycleOwner = getAct()
    }

    private fun injectViewModel() {
        getViewModel()?.let {
            viewModel = ViewModelProvider(getAct())[it]
        }
        viewModel?.let {
            lifecycle.addObserver(it)
            if (isObserveChanged()) {
                it.getLiveData().observeForever(this)
                it.getDialogData().observeForever(this)
            }
        }
    }

    protected abstract fun getViewModel(): Class<VM>?

    open fun isObserveChanged(): Boolean {
        return true
    }

    open fun dataBinding(): DB {
        return dataBinding
    }

    open fun viewModel(): VM? {
        return viewModel
    }

    override fun onChanged(o: Any?) {}

    override fun onDestroy() {
        dataBinding.unbind()
        viewModel?.let {
            lifecycle.removeObserver(it)
        }
        super.onDestroy()
    }
}