package com.lookballs.mvvm.core.binding

import android.util.SparseIntArray
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder

abstract class BaseBindingMultiAdapter<T : MultiItemEntity, DB : ViewDataBinding>(data: MutableList<T>?) : BaseMultiItemQuickAdapter<T, BaseDataBindingHolder<DB>>(data), LoadMoreModule {
    private val BRS = SparseIntArray()

    fun addItemViewType(type: Int, layoutResId: Int, variableId: Int) {
        addItemType(type, layoutResId)
        BRS.put(type, variableId)
    }

    protected override fun convert(holder: BaseDataBindingHolder<DB>, t: T) {
        val dataBinding: ViewDataBinding? = holder.dataBinding
        dataBinding?.setVariable(BRS[holder.itemViewType], t)
        dataBinding?.executePendingBindings()
    }
}