package com.lookballs.app.mvvm.ui.fragment;

import android.os.Bundle;
import android.widget.Toast;

import com.lookballs.app.mvvm.R;
import com.lookballs.app.mvvm.base.AppBindingFragment;
import com.lookballs.app.mvvm.databinding.FragmentTestBindingBinding;
import com.lookballs.mvvm.core.binding.BaseViewModel;

public class TestBindingFragment extends AppBindingFragment<BaseViewModel, FragmentTestBindingBinding> {

    public static TestBindingFragment getInstance(int type) {
        TestBindingFragment fragment = new TestBindingFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    private int type = -1;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test_binding;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        type = getArguments().getInt("type");
        dataBinding().btn.setText("标题" + type);
        dataBinding().setTitle("标题标题" + type);
        Toast.makeText(getAct(), "标题" + type, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Class<BaseViewModel> getViewModel() {
        return BaseViewModel.class;
    }
}
