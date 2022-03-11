package com.lookballs.app.mvvm.ui.adapter;

import androidx.annotation.NonNull;

import com.lookballs.app.mvvm.R;
import com.lookballs.mvvm.core.adapter.BaseBindingHolder;
import com.lookballs.mvvm.core.adapter.BaseBindingMultiAdapter;
import com.lookballs.mvvm.core.adapter.BaseBindingMultiItemBean;

public class TestMultiAdapter extends BaseBindingMultiAdapter {
    public TestMultiAdapter() {
        addItemType(1, R.layout.item_article);
    }

    @Override
    protected void newConvert(@NonNull BaseBindingHolder holder, BaseBindingMultiItemBean itemBean) {

    }

    @Override
    protected boolean isThrowTypeNotFound() {
        return false;
    }
}
