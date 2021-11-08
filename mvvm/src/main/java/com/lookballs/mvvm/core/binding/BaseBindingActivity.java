package com.lookballs.mvvm.core.binding;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.lookballs.mvvm.core.BaseActivity;

/**
 * 创建人：lucas
 * 创建时间：2021/5/15 11:06
 * 类描述：基于MVVM模式的Activity基类
 */
public abstract class BaseBindingActivity<VM extends BaseViewModel, DB extends ViewDataBinding> extends BaseActivity implements Observer<Object> {

    private DB dataBinding;
    private VM viewModel;

    @Override
    public void initContentView() {
        injectDataBinding();
        injectViewModel();
    }

    private void injectDataBinding() {
        dataBinding = DataBindingUtil.setContentView(getAct(), getLayoutId());
        dataBinding.setLifecycleOwner(getAct());
    }

    private void injectViewModel() {
        if (getViewModel() != null) {
            viewModel = new ViewModelProvider(getAct()).get(getViewModel());
            getLifecycle().addObserver(viewModel);
            if (isObserveChanged()) {
                viewModel.getLiveData().observe(getAct(), this);
                viewModel.getDialogData().observe(getAct(), this);
            }
        }
    }

    protected abstract Class<VM> getViewModel();

    public boolean isObserveChanged() {
        return true;
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
