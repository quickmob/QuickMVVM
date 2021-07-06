package com.lookballs.app.mvvm.view.dialog;

import androidx.fragment.app.FragmentActivity;

import com.lookballs.app.mvvm.R;
import com.lookballs.app.mvvm.databinding.DialogTestBindingBinding;
import com.lookballs.mvvm.core.binding.BaseBindingDialog;

public class TestBindingDialog extends BaseBindingDialog<DialogTestBindingBinding> {

    public TestBindingDialog(FragmentActivity activity) {
        super(activity);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_test_binding;
    }

    @Override
    protected void initView() {
        dataBinding().setTitle("我是一个Dialog");
    }

    @Override
    protected void initData() {

    }
}
