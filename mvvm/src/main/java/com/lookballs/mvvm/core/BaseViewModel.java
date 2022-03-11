package com.lookballs.mvvm.core;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lookballs.mvvm.impl.lifecycle.ILifecycleObserver;

/**
 * 创建人：lucas
 * 创建时间：2021/5/15 11:06
 * 类描述：ViewModel基类
 */
public class BaseViewModel extends ViewModel implements ILifecycleObserver {
    @Nullable
    private LifecycleOwner mLifcycleOwner;
    private ViewDataBinding mDataBinding;

    private MutableLiveData<Object> mDialogData = new MutableLiveData<>();//dialog专用
    private MutableLiveData<Object> mActivityData = new MutableLiveData<>();//activity专用
    private MutableLiveData<Object> mFragmentData = new MutableLiveData<>();//fragment专用

    @Override
    public void onLifecycleChanged(LifecycleOwner owner, Lifecycle.Event event) {
        this.mLifcycleOwner = owner;
    }

    public LifecycleOwner getLifecycleOwner() {
        return mLifcycleOwner;
    }

    public MutableLiveData<Object> getDialogData() {
        return mDialogData;
    }

    public MutableLiveData<Object> getActivityData() {
        return mActivityData;
    }

    public MutableLiveData<Object> getFragmentData() {
        return mFragmentData;
    }

    public void setViewDataBinding(ViewDataBinding dataBinding) {
        this.mDataBinding = dataBinding;
    }

    public ViewDataBinding getDataBinding() {
        return mDataBinding;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
