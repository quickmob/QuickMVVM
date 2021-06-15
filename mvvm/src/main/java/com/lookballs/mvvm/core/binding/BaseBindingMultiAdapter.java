package com.lookballs.mvvm.core.binding;

import android.util.SparseIntArray;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;

import java.util.List;

public abstract class BaseBindingMultiAdapter<T extends MultiItemEntity, DB extends ViewDataBinding> extends BaseMultiItemQuickAdapter<T, BaseDataBindingHolder<DB>> implements LoadMoreModule {

    private SparseIntArray BRS = new SparseIntArray();

    public BaseBindingMultiAdapter(List<T> data) {
        super(data);
    }

    public void addItemViewType(int type, int layoutResId, int variableId) {
        addItemType(type, layoutResId);
        BRS.put(type, variableId);
    }

    @Override
    protected void convert(@NonNull BaseDataBindingHolder<DB> holder, T t) {
        ViewDataBinding dataBinding = holder.getDataBinding();
        if (dataBinding != null) {
            dataBinding.setVariable(BRS.get(holder.getItemViewType()), t);
            dataBinding.executePendingBindings();
        }
    }
}
