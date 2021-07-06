package com.lookballs.mvvm.core

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class BaseAdapter<AB>(layoutResId: Int) : BaseQuickAdapter<AB, BaseAdapter.BaseHolder>(layoutResId), LoadMoreModule {
    override fun convert(holder: BaseHolder, ab: AB) {

    }

    class BaseHolder(view: View) : BaseViewHolder(view)
}