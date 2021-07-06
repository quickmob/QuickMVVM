package com.lookballs.mvvm.core.binding;

import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lookballs.mvvm.core.ILifecycleObserver;

/**
 * 创建人：lucas
 * 创建时间：2021/5/15 11:06
 * 类描述：ViewModel基类
 */
public class BaseViewModel extends ViewModel implements ILifecycleObserver {

    private MutableLiveData<Object> mDialogData = new MutableLiveData<>();
    private MutableLiveData<Object> mLiveData = new MutableLiveData<>();

    @Nullable
    private LifecycleOwner mLifcycleOwner;

    @Override
    public void onLifecycleChanged(LifecycleOwner owner, Lifecycle.Event event) {
        this.mLifcycleOwner = owner;
    }

    public MutableLiveData<Object> getDialogData() {
        return mDialogData;
    }

    public MutableLiveData<Object> getLiveData() {
        return mLiveData;
    }

    public LifecycleOwner getLifecycleOwner() {
        return mLifcycleOwner;
    }
}
