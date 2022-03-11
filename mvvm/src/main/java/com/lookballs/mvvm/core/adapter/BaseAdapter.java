package com.lookballs.mvvm.core.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;

/**
 * 创建人：lucas
 * 创建时间：2021/5/15 11:06
 * 类描述：单布局适配器基类
 */
public class BaseAdapter<T> extends BaseQuickAdapter<T, BaseHolder> implements LoadMoreModule {
    public BaseAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseHolder holder, T t) {
        newConvert(holder, t);
    }

    protected void newConvert(@NonNull BaseHolder holder, T t) {

    }
}
