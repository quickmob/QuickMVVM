package com.lookballs.app.mvvm.view.activity;

import com.blankj.utilcode.util.ToastUtils;
import com.lookballs.app.mvvm.R;
import com.lookballs.app.mvvm.base.AppBindingActivity;
import com.lookballs.app.mvvm.bean.BannerJsonBean;
import com.lookballs.app.mvvm.databinding.ActivityTestViewModelBindingBinding;
import com.lookballs.app.mvvm.viewmodel.TestViewModel;

public class TestViewModelBindingActivity extends AppBindingActivity<TestViewModel, ActivityTestViewModelBindingBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_view_model_binding;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        viewModel().getBanner();
    }

    @Override
    protected Class<TestViewModel> getViewModel() {
        return TestViewModel.class;
    }

    @Override
    public void onChanged(Object o) {
        super.onChanged(o);
        if (o instanceof Exception) {
            Exception e = (Exception) o;
            ToastUtils.showLong(e.getMessage());
        } else if (o instanceof BannerJsonBean) {
            BannerJsonBean bean = (BannerJsonBean) o;
            dataBinding().setData(bean);
        }
    }
}
