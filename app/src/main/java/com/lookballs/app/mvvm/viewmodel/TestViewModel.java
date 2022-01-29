package com.lookballs.app.mvvm.viewmodel;

import com.lookballs.app.mvvm.bean.ArticleJsonBean;
import com.lookballs.app.mvvm.bean.BannerJsonBean;
import com.lookballs.app.mvvm.constant.UrlConstant;
import com.lookballs.app.mvvm.constant.ViewModelConstant;
import com.lookballs.http.QuickHttp;
import com.lookballs.http.core.listener.OnHttpListener;
import com.lookballs.mvvm.core.binding.BaseViewModel;

import okhttp3.Call;

public class TestViewModel extends BaseViewModel {
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
                        getLiveData().setValue(result);
                    }

                    @Override
                    public void onError(int code, Exception e) {
                        getLiveData().setValue(code);
                        getLiveData().setValue(e);
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
                        getLiveData().setValue(result);
                    }

                    @Override
                    public void onError(int code, Exception e) {
                        getLiveData().setValue(code);
                        getLiveData().setValue(e);
                    }

                    @Override
                    public void onEnd(Call call) {
                        getDialogData().setValue(ViewModelConstant.DIALOG_DISMISS);
                    }
                });
    }
}
