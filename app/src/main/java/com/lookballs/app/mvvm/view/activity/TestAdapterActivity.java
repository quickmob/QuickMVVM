package com.lookballs.app.mvvm.view.activity;

import android.graphics.Color;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.lookballs.app.mvvm.R;
import com.lookballs.app.mvvm.base.AppBindingActivity;
import com.lookballs.app.mvvm.bean.ArticleJsonBean;
import com.lookballs.app.mvvm.databinding.ActivityTestAdapterBinding;
import com.lookballs.app.mvvm.view.adapter.TestAdapter;
import com.lookballs.app.mvvm.viewmodel.TestViewModel;

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

    private TestAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_adapter;
    }

    @Override
    protected void initView() {
        dataBinding.rvData.setLayoutManager(new LinearLayoutManager(this));
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
        mAdapter = new TestAdapter(R.layout.item_article);
        mAdapter.setAnimationEnable(true);
        dataBinding.rvData.setAdapter(mAdapter);
    }

    private void initRefresh() {
        dataBinding.swipeLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        dataBinding.swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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
        //当自动加载开启，同时数据不满一屏时，是否继续执行自动加载更多(默认为true)
        mAdapter.getLoadMoreModule().setEnableLoadMoreIfNotFullPage(false);
    }

    /**
     * 下拉刷新数据
     */
    private void refreshData() {
        //这里的作用是防止下拉刷新的时候还可以上拉加载
        mAdapter.getLoadMoreModule().setEnableLoadMore(false);
        //下拉刷新，需要重置页数
        pageInfo.reset();
        requestData();
    }

    /**
     * 加载更多数据
     */
    private void loadMoreData() {
        requestData();
    }

    /**
     * 请求数据
     */
    private void requestData() {
        viewModel.getArticle(pageInfo.page);
    }

    @Override
    public void onChanged(Object o) {
        super.onChanged(o);
        if (o instanceof Exception) {
            Exception e = (Exception) o;
            ToastUtils.showLong(e.getMessage());

            dataBinding.swipeLayout.setRefreshing(false);
            mAdapter.getLoadMoreModule().setEnableLoadMore(true);
            mAdapter.getLoadMoreModule().loadMoreFail();
        } else if (o instanceof Integer) {

        } else if (o instanceof ArticleJsonBean) {
            dataBinding.swipeLayout.setRefreshing(false);
            mAdapter.getLoadMoreModule().setEnableLoadMore(true);

            ArticleJsonBean bean = (ArticleJsonBean) o;
            if (bean.getData() != null) {
                if (pageInfo.isFirstPage()) {
                    //如果是加载的第一页数据，用 setData()
                    mAdapter.setList(bean.getData().getDatas());
                } else {
                    //不是第一页，则用add
                    mAdapter.addData(bean.getData().getDatas());
                }
                if (bean.getData().getDatas().size() < PAGE_SIZE) {
                    //如果不够一页,显示没有更多数据布局
                    mAdapter.getLoadMoreModule().loadMoreEnd();
                } else {
                    //否则刷新布局
                    mAdapter.getLoadMoreModule().loadMoreComplete();
                }
                //page加一
                pageInfo.nextPage();
            }
        }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.btn) {
            List<ArticleJsonBean.DataBean.DatasBean> datasBeans = mAdapter.getData();
            if (datasBeans != null && datasBeans.size() > 0) {
                datasBeans.get(0).setLink("我是改变后的内容");
            }
        }
    }
}
