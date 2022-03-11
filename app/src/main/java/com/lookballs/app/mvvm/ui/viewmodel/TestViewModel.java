package com.lookballs.app.mvvm.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.lookballs.app.mvvm.constant.UrlConstant;
import com.lookballs.app.mvvm.constant.ViewModelConstant;
import com.lookballs.app.mvvm.ui.bean.ArticleJsonBean;
import com.lookballs.app.mvvm.ui.bean.BannerJsonBean;
import com.lookballs.http.QuickHttp;
import com.lookballs.http.core.listener.OnHttpListener;
import com.lookballs.mvvm.core.BaseViewModel;

import okhttp3.Call;

public class TestViewModel extends BaseViewModel {
    public MutableLiveData<BannerJsonBean> bannerJsonBean = new MutableLiveData<>();

    public void getBanner() {//获取banner图数据
        QuickHttp.get(UrlConstant.Banner.banner_json)
                .bindLife(getLifecycleOwner())
                .async(new OnHttpListener<BannerJsonBean>() {
                    @Override
                    public void onStart(Call call) {
                        getDialogData().setValue(ViewModelConstant.DIALOG_SHOW);
                    }

                    @Override
                    public void onSucceed(BannerJsonBean result) {
                        getActivityData().setValue(result);
                        //或者
                        bannerJsonBean.setValue(result);
                    }

                    @Override
                    public void onError(int code, Exception e) {
                        getActivityData().setValue(code);
                        getActivityData().setValue(e);
                    }

                    @Override
                    public void onEnd(Call call) {
                        getDialogData().setValue(ViewModelConstant.DIALOG_DISMISS);
                    }
                });
    }

    public void getArticle(int page) {//获取文章数据
        String url = String.format(UrlConstant.Article.article, page);
        QuickHttp.get(url)
                .bindLife(getLifecycleOwner())
                .async(new OnHttpListener<ArticleJsonBean>() {
                    @Override
                    public void onStart(Call call) {
                        if (page == 0) {
                            getDialogData().setValue(ViewModelConstant.DIALOG_SHOW);
                        }
                    }

                    @Override
                    public void onSucceed(ArticleJsonBean result) {
                        getActivityData().setValue(result);
                    }

                    @Override
                    public void onError(int code, Exception e) {
                        getActivityData().setValue(code);
                        getActivityData().setValue(e);
                    }

                    @Override
                    public void onEnd(Call call) {
                        getDialogData().setValue(ViewModelConstant.DIALOG_DISMISS);
                    }
                });
    }
}
