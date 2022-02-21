package com.lookballs.mvvm.core;

import android.view.View;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

public class BaseAdapter<AB> extends BaseQuickAdapter<AB, BaseAdapter.BaseHolder> implements LoadMoreModule {
    public BaseAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseHolder holder, AB ab) {

    }

    public static class BaseHolder extends BaseViewHolder {
        public BaseHolder(@NonNull View view) {
            super(view);
        }
    }
}
