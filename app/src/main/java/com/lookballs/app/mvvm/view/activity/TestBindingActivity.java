package com.lookballs.app.mvvm.view.activity;

import android.view.View;

import androidx.fragment.app.Fragment;

import com.lookballs.app.mvvm.R;
import com.lookballs.app.mvvm.view.fragment.TestBindingFragment;
import com.lookballs.app.mvvm.databinding.ActivityTestBindingBinding;
import com.lookballs.app.mvvm.base.AppBindingActivity;
import com.lookballs.mvvm.BaseFragmentPagerAdapter;
import com.lookballs.mvvm.core.binding.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

public class TestBindingActivity extends AppBindingActivity<BaseViewModel, ActivityTestBindingBinding> {

    private List<String> mTitles = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();
    private BaseFragmentPagerAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_binding;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mAdapter = new BaseFragmentPagerAdapter(getSupportFragmentManager());

//        mAdapter.addFragment(TestBindingFragment.getInstance(1), "标题1");
//        mAdapter.addFragment(TestBindingFragment.getInstance(2), "标题2");
//        mAdapter.addFragment(TestBindingFragment.getInstance(3), "标题3");
//        mAdapter.addFragment(TestBindingFragment.getInstance(4), "标题4");
//        mAdapter.addFragment(TestBindingFragment.getInstance(5), "标题5");

        for (int i = 0; i < 5; i++) {
            mTitles.add("标题" + (i + 1));
            mFragments.add(TestBindingFragment.getInstance(i + 1));
        }
        mAdapter.addFragments(mFragments, mTitles);

        dataBinding().viewPager.setAdapter(mAdapter);
        dataBinding().tabLayout.setupWithViewPager(dataBinding().viewPager);
    }

    @Override
    protected Class<BaseViewModel> getViewModel() {
        return BaseViewModel.class;
    }

    public void btn1(View view) {
//        mAdapter.removeFragment(0);

        mFragments.remove(0);
        mTitles.remove(0);
        mAdapter.addFragments(mFragments, mTitles);

        dataBinding().viewPager.setAdapter(mAdapter);
        dataBinding().tabLayout.setupWithViewPager(dataBinding().viewPager);
    }
}
