package com.lookballs.mvvm.core.adapter;

import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.lookballs.mvvm.BR;
import com.lookballs.mvvm.R;

import java.util.List;

/**
 * 创建人：lucas
 * 创建时间：2021/5/15 11:06
 * 类描述：多布局适配器基类
 */
public abstract class BaseBindingMultiAdapter extends BaseQuickAdapter<BaseBindingMultiItemBean, BaseBindingHolder> implements LoadMoreModule {
    private SparseIntArray layouts = new SparseIntArray();

    public BaseBindingMultiAdapter() {
        super(0);
    }

    public void addItemType(int type, @LayoutRes int layoutResId) {
        layouts.put(type, layoutResId);
    }

    @Override
    protected int getDefItemViewType(int position) {
        return getData().get(position).getItemType();
    }

    @Override
    protected BaseBindingHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        int layoutResId = layouts.get(viewType);
        if (layoutResId != 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(layoutResId, parent, false);
            return createBaseViewHolder(view);
        } else {
            if (isThrowTypeNotFound()) {
                throw new IllegalArgumentException("viewType " + viewType + " not found，please use addItemType() first");
            }
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.base_type_not_found, parent, false);
            return createBaseViewHolder(view);
        }
    }

    @Override
    protected void onItemViewHolderCreated(BaseBindingHolder holder, int viewType) {
        //绑定View
        DataBindingUtil.bind(holder.itemView);
    }

    @Override
    protected void convert(BaseBindingHolder holder, BaseBindingMultiItemBean itemBean) {
        ViewDataBinding dataBinding = holder.getDataBinding();
        if (dataBinding != null) {
            dataBinding.setVariable(BR.data, itemBean);
            dataBinding.executePendingBindings();

            newConvert(holder, itemBean);
        }
    }

    @Override
    protected void convert(BaseBindingHolder holder, BaseBindingMultiItemBean itemBean, List<?> payloads) {
        ViewDataBinding dataBinding = holder.getDataBinding();
        if (dataBinding != null) {
            dataBinding.setVariable(BR.data, itemBean);
            dataBinding.executePendingBindings();

            newConvert(holder, itemBean, payloads);
        }
    }

    protected boolean isThrowTypeNotFound() {
        return true;
    }

    protected void newConvert(@NonNull BaseBindingHolder holder, BaseBindingMultiItemBean itemBean) {

    }

    protected void newConvert(@NonNull BaseBindingHolder holder, BaseBindingMultiItemBean itemBean, List<?> payloads) {

    }
}
