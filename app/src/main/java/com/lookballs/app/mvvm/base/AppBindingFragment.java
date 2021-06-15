package com.lookballs.app.mvvm.base;

import androidx.databinding.ViewDataBinding;

import com.lookballs.app.mvvm.constant.ViewModelConstant;
import com.lookballs.app.mvvm.view.dialog.LoadingDialog;
import com.lookballs.mvvm.core.binding.BaseBindingFragment;
import com.lookballs.mvvm.core.binding.BaseViewModel;

/**
 * 创建人：lucas
 * 创建时间：2021/5/15 11:06
 * 类描述：业务Fragment基类
 */
public abstract class AppBindingFragment<VM extends BaseViewModel, DB extends ViewDataBinding> extends BaseBindingFragment<VM, DB> {

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
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
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
        if (!isShowDialog() && (activity == null || activity.isFinishing() || activity.isDestroyed())) {
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
                if (activity != null && activity instanceof AppBindingActivity) {
                    ((AppBindingActivity) activity).showDialog();
                } else {
                    showDialog();
                }
            } else if (code == ViewModelConstant.DIALOG_DISMISS) {
                if (activity != null && activity instanceof AppBindingActivity) {
                    ((AppBindingActivity) activity).dismissDialog();
                } else {
                    dismissDialog();
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        loadingDialog = null;
    }
}