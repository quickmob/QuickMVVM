package com.lookballs.mvvm.core.fragment;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.lookballs.mvvm.BR;
import com.lookballs.mvvm.core.BaseViewModel;

/**
 * 创建人：lucas
 * 创建时间：2021/5/15 11:06
 * 类描述：基于MVVM模式的Fragment基类
 */
public abstract class BaseBindingFragment<VM extends BaseViewModel, DB extends ViewDataBinding> extends BaseFragment implements Observer<Object> {
    private DB dataBinding;
    private VM viewModel;

    /**
     * 子类可以实现的自定义方法
     */
    protected abstract Class<VM> getViewModel();

    public boolean isObserveChanged() {
        return true;
    }

    @Override
    public void initContentView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        injectDataBinding(inflater, container);
        injectViewModel();
        initVariable();
    }

    private void injectDataBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        dataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        dataBinding.setLifecycleOwner(this);
        setRootView(dataBinding.getRoot());
    }

    private void injectViewModel() {
        viewModel = new ViewModelProvider(this).get(getViewModel());
        viewModel.setViewDataBinding(dataBinding);
        getLifecycle().addObserver(viewModel);
        if (isObserveChanged()) {
            viewModel.getDialogData().observe(this, this);
            viewModel.getFragmentData().observe(this, this);
        }
    }

    private void initVariable() {
        dataBinding.setVariable(BR.viewModel, viewModel);
        dataBinding.setVariable(BR.fragment, this);
    }

    public DB dataBinding() {
        return dataBinding;
    }

    public VM viewModel() {
        return viewModel;
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
