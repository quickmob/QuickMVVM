package com.lookballs.app.mvvm.ui.dialog;

import androidx.fragment.app.FragmentActivity;

import com.lookballs.app.mvvm.R;
import com.lookballs.app.mvvm.databinding.DialogLoadingBinding;
import com.lookballs.mvvm.core.binding.BaseBindingDialog;

public class LoadingDialog extends BaseBindingDialog<DialogLoadingBinding> {

    private String tips = "加载中";
    private boolean isCancelable = true;

    public LoadingDialog(FragmentActivity activity) {
        super(activity);
    }

    public LoadingDialog(FragmentActivity activity, boolean isCancelable) {
        super(activity);
        this.isCancelable = isCancelable;
    }

    public LoadingDialog(FragmentActivity activity, String tips) {
        super(activity);
        this.tips = tips;
    }

    public LoadingDialog(FragmentActivity activity, String tips, boolean isCancelable) {
        super(activity);
        this.tips = tips;
        this.isCancelable = isCancelable;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_loading;
    }

    @Override
    protected void initView() {
        dataBinding().setData(tips);
        setBackgroundDimEnabled(false);
    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean setCanceledOnTouchOutside() {
        return false;
    }

    @Override
    public boolean setCancelable() {
        return isCancelable;
    }
}
