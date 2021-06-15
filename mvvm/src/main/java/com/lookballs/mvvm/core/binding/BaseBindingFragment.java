package com.lookballs.mvvm.core.binding;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.lookballs.mvvm.core.BaseFragment;

/**
 * 创建人：lucas
 * 创建时间：2021/5/15 11:06
 * 类描述：基于MVVM模式的Fragment基类
 */
public abstract class BaseBindingFragment<VM extends BaseViewModel, DB extends ViewDataBinding> extends BaseFragment implements Observer<Object> {

    protected DB dataBinding;
    protected VM viewModel;

    @Override
    protected void initContentView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        injectDataBinding(inflater, container);
        if (getViewModel() != null) {
            injectViewModel();
        }
    }

    private void injectDataBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        dataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        dataBinding.setLifecycleOwner(activity);
        setRootView(dataBinding.getRoot());
    }

    private void injectViewModel() {
        viewModel = new ViewModelProvider(activity).get(getViewModel());
        getLifecycle().addObserver(viewModel);
        if (isObserveChanged()) {
            viewModel.mLiveData.observeForever(this);
            viewModel.mDialogData.observeForever(this);
        }
    }

    protected Class<VM> getViewModel() {
        return null;
    }

    protected boolean isObserveChanged() {
        return true;
    }

    @Override
    public void onChanged(Object o) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (dataBinding != null) {
            dataBinding.unbind();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (viewModel != null) {
            getLifecycle().removeObserver(viewModel);
        }
    }
}