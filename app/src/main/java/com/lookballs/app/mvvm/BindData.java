package com.lookballs.app.mvvm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import com.lookballs.app.mvvm.bean.BannerJsonBean;
import com.lookballs.app.mvvm.databinding.ItemBannerBinding;

import java.util.List;

public class BindData {
    @BindingAdapter(value = {"addBannerViews"})
    public static void addBannerViews(ViewGroup viewGroup, List<BannerJsonBean.DataBean> list) {
        viewGroup.removeAllViews();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_banner, null);
                ItemBannerBinding dataBinding = DataBindingUtil.bind(view);
                dataBinding.setData(list.get(i).getTitle());

                viewGroup.addView(view);
            }
        }
    }
}
