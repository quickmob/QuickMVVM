package com.lookballs.mvvm.core.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;

/**
 * 创建人：lucas
 * 创建时间：2021/5/15 11:06
 * 类描述：ViewHolder基类
 */
public class BaseBindingHolder extends BaseHolder {
    public BaseBindingHolder(@NonNull View view) {
        super(view);
    }

    public ViewDataBinding getDataBinding() {
        return DataBindingUtil.getBinding(itemView);
    }
}