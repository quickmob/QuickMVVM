package com.lookballs.app.mvvm.ui.adapter;

import androidx.annotation.NonNull;

import com.lookballs.app.mvvm.R;
import com.lookballs.mvvm.core.adapter.BaseBindingAdapter;
import com.lookballs.mvvm.core.adapter.BaseBindingHolder;
import com.lookballs.mvvm.core.adapter.BaseBindingItemBean;

public class TestAdapter extends BaseBindingAdapter {
    public TestAdapter() {
        super(R.layout.item_article);
    }

    @Override
    protected void newConvert(@NonNull BaseBindingHolder holder, BaseBindingItemBean itemBean) {
        
    }
}
