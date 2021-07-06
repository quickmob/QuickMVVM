package com.lookballs.mvvm.core.binding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lookballs.mvvm.core.BaseFragment

/**
 * 创建人：lucas
 * 创建时间：2021/5/15 11:06
 * 类描述：基于MVVM模式的Fragment基类
 */
abstract class BaseBindingFragment<VM : BaseViewModel, DB : ViewDataBinding> : BaseFragment(), Observer<Any?> {
    private lateinit var dataBinding: DB
    private var viewModel: VM? = null

    override fun initContentView(inflater: LayoutInflater, container: ViewGroup?) {
        injectDataBinding(inflater, container)
        injectViewModel()
    }

    private fun injectDataBinding(inflater: LayoutInflater, container: ViewGroup?) {
        dataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        dataBinding.lifecycleOwner = getAct()
        setRootView(dataBinding.root)
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

    protected abstract fun getViewModel(): Class<VM>

    open fun isObserveChanged(): Boolean {
        return true
    }

    open fun dataBinding(): DB {
        return dataBinding
    }

    open fun viewModel(): VM? {
        return viewModel;
    }

    override fun onChanged(o: Any?) {}

    override fun onDestroyView() {
        super.onDestroyView()
        dataBinding.unbind()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel?.let {
            lifecycle.removeObserver(it)
        }
    }
}