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

    protected DB dataBinding;
    protected VM viewModel;

    @Override
    protected void initContentView() {
        injectDataBinding();
        if (getViewModel() != null) {
            injectViewModel();
        }
    }

    private void injectDataBinding() {
        dataBinding = DataBindingUtil.setContentView(activity, getLayoutId());
        dataBinding.setLifecycleOwner(activity);
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
