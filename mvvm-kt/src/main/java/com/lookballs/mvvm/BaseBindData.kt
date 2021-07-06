package com.lookballs.mvvm

import android.view.View
import androidx.databinding.BindingAdapter

/**
 * 创建人：lucas
 * 创建时间：2021/5/15 11:06
 * 类描述：BindingAdapter基础类
 */
object BaseBindData {
    @BindingAdapter(value = ["setVisibility"])
    fun setVisibility(view: View, isVisibility: Boolean) {
        view.visibility = if (isVisibility) View.VISIBLE else View.INVISIBLE
    }

    @BindingAdapter(value = ["setGone"])
    fun setGone(view: View, isGone: Boolean) {
        view.visibility = if (isGone) View.VISIBLE else View.GONE
    }

    @BindingAdapter(value = ["setSelected"])
    fun setSelected(view: View, isSelected: Boolean) {
        view.isSelected = isSelected
    }

    @BindingAdapter(value = ["isClickable"])
    fun setClickable(view: View, isClickable: Boolean) {
        view.isClickable = isClickable
    }
}