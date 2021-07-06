package com.lookballs.mvvm.core.binding

import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder

abstract class BaseBindingAdapter<AB, DB : ViewDataBinding>(layoutResId: Int, private val variableId: Int) : BaseQuickAdapter<AB, BaseDataBindingHolder<DB>>(layoutResId), LoadMoreModule {
    override fun convert(holder: BaseDataBindingHolder<DB>, ab: AB) {
        val dataBinding: ViewDataBinding? = holder.dataBinding
        dataBinding?.setVariable(variableId, ab)
        dataBinding?.executePendingBindings()
    }
}