package com.lookballs.app.mvvm.ui.activity;

import android.graphics.Color;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.lookballs.app.mvvm.R;
import com.lookballs.app.mvvm.base.AppBindingActivity;
import com.lookballs.app.mvvm.databinding.ActivityTestAdapterBinding;
import com.lookballs.app.mvvm.ui.adapter.TestMultiAdapter;
import com.lookballs.app.mvvm.ui.bean.ArticleJsonBean;
import com.lookballs.app.mvvm.ui.viewmodel.TestViewModel;
import com.lookballs.mvvm.core.adapter.BaseBindingMultiItemBean;

import java.util.List;

public class TestAdapterActivity extends AppBindingActivity<TestViewModel, ActivityTestAdapterBinding> {

    class PageInfo {
        int page = 0;

        void nextPage() {
            page++;
        }

        void reset() {
            page = 0;
        }

        boolean isFirstPage() {
            return page == 0;
        }
    }

    private final int PAGE_SIZE = 20;
    private PageInfo pageInfo = new PageInfo();

    //    private TestAdapter mAdapter;
    private TestMultiAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_adapter;
    }

    @Override
    protected void initView() {
        dataBinding().rvData.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        initAdapter();
        initRefresh();
        initLoadMore();

        requestData();
    }

    @Override
    protected Class<TestViewModel> getViewModel() {
        return TestViewModel.class;
    }

    private void initAdapter() {
//        mAdapter = new TestAdapter();
        mAdapter = new TestMultiAdapter();
        mAdapter.setAnimationEnable(true);
        dataBinding().rvData.setAdapter(mAdapter);
    }

    private void initRefresh() {
        dataBinding().swipeLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        dataBinding().swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });
    }

    private void initLoadMore() {
        mAdapter.getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                loadMoreData();
            }
        });
        mAdapter.getLoadMoreModule().setAutoLoadMore(true);
        //??????????????????????????????????????????????????????????????????????????????????????????(?????????true)
        mAdapter.getLoadMoreModule().setEnableLoadMoreIfNotFullPage(false);
    }

    /**
     * ??????????????????
     */
    private void refreshData() {
        //??????????????????????????????????????????????????????????????????
        mAdapter.getLoadMoreModule().setEnableLoadMore(false);
        //?????????????????????????????????
        pageInfo.reset();
        requestData();
    }

    /**
     * ??????????????????
     */
    private void loadMoreData() {
        requestData();
    }

    /**
     * ????????????
     */
    private void requestData() {
        viewModel().getArticle(pageInfo.page);
    }

    @Override
    public void onChanged(Object o) {
        super.onChanged(o);
        if (o instanceof Exception) {
            Exception e = (Exception) o;
            ToastUtils.showLong(e.getMessage());

            dataBinding().swipeLayout.setRefreshing(false);
            mAdapter.getLoadMoreModule().setEnableLoadMore(true);
            mAdapter.getLoadMoreModule().loadMoreFail();
        } else if (o instanceof Integer) {

        } else if (o instanceof ArticleJsonBean) {
            dataBinding().swipeLayout.setRefreshing(false);
            mAdapter.getLoadMoreModule().setEnableLoadMore(true);

            ArticleJsonBean bean = (ArticleJsonBean) o;
            if (bean.getData() != null) {
                if (pageInfo.isFirstPage()) {
                    //??????????????????????????????????????? setData()
                    mAdapter.setList(bean.getData().getDatas());
                } else {
                    //????????????????????????add
                    mAdapter.addData(bean.getData().getDatas());
                }
                if (bean.getData().getDatas().size() < PAGE_SIZE) {
                    //??????????????????,??????????????????????????????
                    mAdapter.getLoadMoreModule().loadMoreEnd();
                } else {
                    //??????????????????
                    mAdapter.getLoadMoreModule().loadMoreComplete();
                }
                //page??????
                pageInfo.nextPage();
            }
        }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.btn) {
//            List<ArticleJsonBean.DataBean.DatasBean> datasBeans = mAdapter.getData();
//            if (datasBeans != null && datasBeans.size() > 0) {
//                datasBeans.get(0).setLink("????????????????????????");
//            }
            List<BaseBindingMultiItemBean> datasBeans = mAdapter.getData();
            if (datasBeans != null && datasBeans.size() > 0) {
                if (datasBeans.get(0) instanceof ArticleJsonBean.DataBean.DatasBean) {
                    ArticleJsonBean.DataBean.DatasBean datasBean = (ArticleJsonBean.DataBean.DatasBean) datasBeans.get(0);
                    datasBean.setLink("????????????????????????");
                }
            }
        }
    }
}
