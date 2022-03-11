package com.lookballs.mvvm.core.dialog;

import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.StyleRes;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.lookballs.mvvm.core.dialog.BaseDialog;

/**
 * 创建人：lucas
 * 创建时间：2021/5/15 11:06
 * 类描述：基于ViewDataBinding的Dialog基类
 */
public abstract class BaseBindingDialog<DB extends ViewDataBinding> extends BaseDialog {
    private DB dataBinding;

    public BaseBindingDialog(FragmentActivity activity) {
        super(activity);
    }

    public BaseBindingDialog(FragmentActivity activity, @StyleRes int themeResId) {
        super(activity, themeResId);
    }

    @Override
    public void initContentView() {
        injectDataBinding();
    }

    private void injectDataBinding() {
        // 这里解释一下，为什么要传 new FrameLayout，因为如果不传的话，XML 的根布局获取到的 LayoutParams 对象会为空，也就会导致宽高参数解析不出来
        dataBinding = DataBindingUtil.inflate(LayoutInflater.from(getAct()), getLayoutId(), new FrameLayout(getAct()), false);
        setRootView(dataBinding.getRoot());
        setContentView(getRootView());
    }

    public DB dataBinding() {
        return dataBinding;
    }

    @Override
    public void onLifecycleChanged(LifecycleOwner owner, Lifecycle.Event event) {
        if (event.equals(Lifecycle.Event.ON_DESTROY)) {
            if (isShowing()) {
                if (dataBinding != null) {
                    dataBinding.unbind();
                }
            }
        }
        super.onLifecycleChanged(owner, event);
    }
}
