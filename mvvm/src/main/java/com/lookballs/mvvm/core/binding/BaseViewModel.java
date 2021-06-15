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

    protected MutableLiveData<Object> mDialogData = new MutableLiveData<>();
    protected MutableLiveData<Object> mLiveData = new MutableLiveData<>();

    @Nullable
    protected LifecycleOwner mLifcycleOwner;

    @Override
    public void onLifecycleChanged(LifecycleOwner owner, Lifecycle.Event event) {
        this.mLifcycleOwner = owner;
    }
}
