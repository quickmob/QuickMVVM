package com.lookballs.app.mvvm.base;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

import com.lookballs.app.mvvm.R;
import com.lookballs.app.mvvm.constant.ViewModelConstant;
import com.lookballs.app.mvvm.view.dialog.LoadingDialog;
import com.lookballs.mvvm.core.binding.BaseBindingActivity;
import com.lookballs.mvvm.core.binding.BaseViewModel;

/**
 * 创建人：lucas
 * 创建时间：2021/5/15 11:06
 * 类描述：业务Activity基类
 */
public abstract class AppBindingActivity<VM extends BaseViewModel, DB extends ViewDataBinding> extends BaseBindingActivity<VM, DB> {

    private LoadingDialog loadingDialog = null;

    /**
     * 当前加载对话框是否在显示中
     */
    public boolean isShowDialog() {
        return loadingDialog != null && loadingDialog.isShowing();
    }

    /**
     * 显示加载对话框
     */
    public void showDialog() {
        if (activity == null || isFinishing() || isDestroyed()) {
            return;
        }
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(activity);
        }
        if (!loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }

    /**
     * 隐藏加载对话框
     */
    public void dismissDialog() {
        if (!isShowDialog() && (activity == null || isFinishing() || isDestroyed())) {
            return;
        }
        loadingDialog.dismiss();
    }

    @Override
    public void onChanged(Object o) {
        super.onChanged(o);
        if (o instanceof Integer) {
            int code = (int) o;
            if (code == ViewModelConstant.DIALOG_SHOW) {
                showDialog();
            } else if (code == ViewModelConstant.DIALOG_DISMISS) {
                dismissDialog();
            }
        }
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
        overridePendingTransition(R.anim.right_in_activity, R.anim.right_out_activity);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.left_in_activity, R.anim.left_out_activity);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loadingDialog = null;
    }
}
