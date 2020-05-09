package com.holiday.jetpackstudy.paging.network;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.holiday.jetpackstudy.R;
import com.holiday.jetpackstudy.databinding.ActivityPagingNetworkBinding;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

/**
 * 非paging方案，用来对比
 */
public class PagingNetworkActivity extends AppCompatActivity {
    PagingNetworkViewModel mViewModel;
    ActivityPagingNetworkBinding mBinding;
    NetworkListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_paging_network);
        mViewModel = ViewModelProviders.of(this).get(PagingNetworkViewModel.class);

        mBinding.refreshArticle.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mAdapter.clearData();
                mViewModel.reqData(false);
            }
        });
        mBinding.refreshArticle.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mViewModel.reqData(true);
            }
        });
        mAdapter = new NetworkListAdapter();
        mBinding.rvArticle.setAdapter(mAdapter);
        mBinding.rvArticle.setLayoutManager(new LinearLayoutManager(this));

        mViewModel.mData.observe(this, new Observer<List<ArticleBean.DataBean.Article>>() {
            @Override
            public void onChanged(List<ArticleBean.DataBean.Article> articles) {
                if (null != articles && !articles.isEmpty()) {
                    mAdapter.addData(articles);
                }
                RefreshState state = mBinding.refreshArticle.getState();
                if (state.isFooter && state.isOpening) {
                    mBinding.refreshArticle.finishLoadMore();
                } else if (state.isHeader && state.isOpening) {
                    mBinding.refreshArticle.finishRefresh();
                }
            }
        });

        mViewModel.reqData(false);
    }


}
