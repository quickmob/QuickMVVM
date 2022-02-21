package com.lookballs.app.mvvm.ui.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.lookballs.app.mvvm.BR;
import com.lookballs.app.mvvm.ui.bean.ArticleJsonBean;
import com.lookballs.app.mvvm.databinding.ItemArticleBinding;
import com.lookballs.mvvm.core.binding.BaseBindingAdapter;

public class TestAdapter extends BaseBindingAdapter<ArticleJsonBean.DataBean.DatasBean, ItemArticleBinding> {
    public TestAdapter(int layoutResId) {
        super(layoutResId, BR.data);
    }

    @Override
    protected void convert(@NonNull BaseDataBindingHolder<ItemArticleBinding> holder, ArticleJsonBean.DataBean.DatasBean dataBean) {
        super.convert(holder, dataBean);
    }
}
