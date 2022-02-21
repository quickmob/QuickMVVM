package com.lookballs.app.mvvm.ui.popup;

import androidx.fragment.app.FragmentActivity;

import com.lookballs.app.mvvm.R;
import com.lookballs.app.mvvm.databinding.PopupwindowTestBindingBinding;
import com.lookballs.mvvm.core.binding.BaseBindingPopupWindow;

public class TestBindingPopupWindow extends BaseBindingPopupWindow<PopupwindowTestBindingBinding> {

    public TestBindingPopupWindow(FragmentActivity activity) {
        super(activity);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.popupwindow_test_binding;
    }

    @Override
    protected void initView() {
        dataBinding().setTitle("我是一个PopupWindow");
    }

    @Override
    protected void initData() {

    }
}
