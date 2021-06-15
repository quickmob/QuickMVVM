package com.lookballs.mvvm;

import android.view.View;

import androidx.databinding.BindingAdapter;

/**
 * 创建人：lucas
 * 创建时间：2021/5/15 11:06
 * 类描述：BindingAdapter基础类
 */
public class BaseBindData {
    @BindingAdapter(value = {"setVisibility"})
    public static void setVisibility(View view, boolean isVisibility) {
        view.setVisibility(isVisibility ? View.VISIBLE : View.INVISIBLE);
    }

    @BindingAdapter(value = {"setGone"})
    public static void setGone(View view, boolean isGone) {
        view.setVisibility(isGone ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter(value = {"setSelected"})
    public static void setSelected(View view, boolean isSelected) {
        view.setSelected(isSelected);
    }

    @BindingAdapter(value = {"isClickable"})
    public static void setClickable(View view, boolean isClickable) {
        view.setClickable(isClickable);
    }
}
