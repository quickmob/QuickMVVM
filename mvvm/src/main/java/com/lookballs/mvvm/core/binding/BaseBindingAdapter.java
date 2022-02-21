package com.lookballs.mvvm.core.binding;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;

public abstract class BaseBindingAdapter<AB, DB extends ViewDataBinding> extends BaseQuickAdapter<AB, BaseDataBindingHolder<DB>> implements LoadMoreModule {
    private int variableId;

    public BaseBindingAdapter(int layoutResId, int variableId) {
        super(layoutResId);
        this.variableId = variableId;
    }

    @Override
    protected void convert(@NonNull BaseDataBindingHolder<DB> holder, AB ab) {
        ViewDataBinding dataBinding = holder.getDataBinding();
        if (dataBinding != null) {
            dataBinding.setVariable(variableId, ab);
            dataBinding.executePendingBindings();
        }
    }
}
