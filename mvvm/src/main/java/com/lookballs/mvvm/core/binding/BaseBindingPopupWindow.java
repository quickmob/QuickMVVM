package com.lookballs.mvvm.core.binding;

import android.view.LayoutInflater;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.lookballs.mvvm.core.BasePopupWindow;

/**
 * 创建人：lucas
 * 创建时间：2021/5/15 11:06
 * 类描述：基于ViewDataBinding的PopupWindow基类
 */
public abstract class BaseBindingPopupWindow<DB extends ViewDataBinding> extends BasePopupWindow {

    protected DB dataBinding;

    public BaseBindingPopupWindow(FragmentActivity activity) {
        super(activity);
    }

    @Override
    protected void initContentView() {
        injectDataBinding();
    }

    private void injectDataBinding() {
        dataBinding = DataBindingUtil.inflate(LayoutInflater.from(activity), getLayoutId(), null, false);
        setRootView(dataBinding.getRoot());
        setContentView(getRootView());
    }

    @Override
    public void onLifecycleChanged(LifecycleOwner owner, Lifecycle.Event event) {
        if (event.equals(Lifecycle.Event.ON_DESTROY)) {
            if (isShowing()) {
                dataBinding.unbind();
            }
        }
        super.onLifecycleChanged(owner, event);
    }
}
