package com.holiday.jetpackstudy.paging.network;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.holiday.jetpackstudy.R;
import com.holiday.jetpackstudy.databinding.ActivityPagingNetworkBinding;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * paging方案
 */
public class PagingNetworkActivity2 extends AppCompatActivity {
    PagingNetworkViewModel2 mViewModel;
    ActivityPagingNetworkBinding mBinding;
    NetworkListAdapter2 mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_paging_network);
        mViewModel = ViewModelProviders.of(this).get(PagingNetworkViewModel2.class);

        //关闭加载更多，使用paging的预加载即可
        mBinding.refreshArticle.setEnableLoadMore(false);
        mBinding.refreshArticle.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //下拉刷新
                mViewModel.refresh();
            }
        });
        mAdapter = new NetworkListAdapter2();
        mBinding.rvArticle.setAdapter(mAdapter);
        mBinding.rvArticle.setLayoutManager(new LinearLayoutManager(this));

        mViewModel.getPageData().observe(this, new Observer<PagedList<ArticleBean.DataBean.Article>>() {
            @Override
            public void onChanged(PagedList<ArticleBean.DataBean.Article> articles) {
                if (null != articles && !articles.isEmpty()) {
                    mAdapter.submitList(articles);
                }
                mBinding.refreshArticle.finishRefresh();
            }
        });

    }


}
