package com.holiday.jetpackstudy.paging.network;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

/**
 * 非paging方案，用来对比
 */
public class PagingNetworkViewModel extends ViewModel {
    private int mPage;
    public MutableLiveData<List<ArticleBean.DataBean.Article>> mData = new MutableLiveData<>();

    public void reqData(boolean loadMore) {
        if (!loadMore) {
            mPage = 0;
        }
        Api.getArticle(String.valueOf(mPage), new Api.ArticleCallback() {
            @Override
            public void onSuccess(ArticleBean.DataBean data) {
                mData.postValue(data.getDatas());
                mPage++;
            }

            @Override
            public void onFail() {
                mData.postValue(null);
            }
        });
    }
}
