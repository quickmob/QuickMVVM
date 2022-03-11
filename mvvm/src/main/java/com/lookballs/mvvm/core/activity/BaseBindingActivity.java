package com.lookballs.mvvm.core.activity;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.lookballs.mvvm.BR;
import com.lookballs.mvvm.core.BaseViewModel;

/**
 * 创建人：lucas
 * 创建时间：2021/5/15 11:06
 * 类描述：基于MVVM模式的Activity基类
 */
public abstract class BaseBindingActivity<VM extends BaseViewModel, DB extends ViewDataBinding> extends BaseActivity implements Observer<Object> {
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
    public void initContentView() {
        injectDataBinding();
        injectViewModel();
        initVariable();
    }

    private void injectDataBinding() {
        dataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        dataBinding.setLifecycleOwner(this);
    }

    private void injectViewModel() {
        viewModel = new ViewModelProvider(this).get(getViewModel());
        viewModel.setViewDataBinding(dataBinding);
        getLifecycle().addObserver(viewModel);
        if (isObserveChanged()) {
            viewModel.getDialogData().observe(this, this);
            viewModel.getActivityData().observe(this, this);
        }
    }

    private void initVariable() {
        dataBinding.setVariable(BR.viewModel, viewModel);
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
    protected void onDestroy() {
        if (dataBinding != null) {
            dataBinding.unbind();
        }
        if (viewModel != null) {
            getLifecycle().removeObserver(viewModel);
        }
        super.onDestroy();
    }
}
