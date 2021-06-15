package com.lookballs.app.mvvm.viewmodel;

import com.lookballs.app.mvvm.bean.ArticleJsonBean;
import com.lookballs.app.mvvm.bean.BannerJsonBean;
import com.lookballs.app.mvvm.constant.UrlConstant;
import com.lookballs.app.mvvm.constant.ViewModelConstant;
import com.lookballs.http.QuickHttp;
import com.lookballs.http.listener.OnHttpListener;
import com.lookballs.mvvm.core.binding.BaseViewModel;

import okhttp3.Call;

public class TestViewModel extends BaseViewModel {
    public void getBanner() {//获取banner图数据
        QuickHttp.get(UrlConstant.Banner.banner_json)
                .bindLife(mLifcycleOwner)
                .async(new OnHttpListener<BannerJsonBean>() {
                    @Override
                    public void onStart(Call call) {
                        mDialogData.setValue(ViewModelConstant.DIALOG_SHOW);
                    }

                    @Override
                    public void onSucceed(BannerJsonBean result) {
                        mLiveData.setValue(result);
                    }

                    @Override
                    public void onError(int code, Exception e) {
                        mLiveData.setValue(code);
                        mLiveData.setValue(e);
                    }

                    @Override
                    public void onEnd(Call call) {
                        mDialogData.setValue(ViewModelConstant.DIALOG_DISMISS);
                    }
                });
    }

    public void getArticle(int page) {//获取文章数据
        String url = String.format(UrlConstant.Article.article, page);
        QuickHttp.get(url)
                .bindLife(mLifcycleOwner)
                .async(new OnHttpListener<ArticleJsonBean>() {
                    @Override
                    public void onStart(Call call) {
                        if (page == 0) {
                            mDialogData.setValue(ViewModelConstant.DIALOG_SHOW);
                        }
                    }

                    @Override
                    public void onSucceed(ArticleJsonBean result) {
                        mLiveData.setValue(result);
                    }

                    @Override
                    public void onError(int code, Exception e) {
                        mLiveData.setValue(code);
                        mLiveData.setValue(e);
                    }

                    @Override
                    public void onEnd(Call call) {
                        mDialogData.setValue(ViewModelConstant.DIALOG_DISMISS);
                    }
                });
    }
}
