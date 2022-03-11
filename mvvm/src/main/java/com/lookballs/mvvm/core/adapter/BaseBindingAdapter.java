package com.lookballs.mvvm.core.adapter;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.lookballs.mvvm.BR;

/**
 * 创建人：lucas
 * 创建时间：2021/5/15 11:06
 * 类描述：单布局适配器基类
 */
public abstract class BaseBindingAdapter extends BaseQuickAdapter<BaseBindingItemBean, BaseBindingHolder> implements LoadMoreModule {
    public BaseBindingAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseBindingHolder holder, BaseBindingItemBean itemBean) {
        ViewDataBinding dataBinding = holder.getDataBinding();
        if (dataBinding != null) {
            dataBinding.setVariable(BR.data, itemBean);
            dataBinding.executePendingBindings();

            newConvert(holder, itemBean);
        }
    }

    protected void newConvert(@NonNull BaseBindingHolder holder, BaseBindingItemBean itemBean) {

    }
}
