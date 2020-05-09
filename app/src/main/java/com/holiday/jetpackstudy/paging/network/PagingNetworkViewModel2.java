package com.holiday.jetpackstudy.paging.network;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.ItemKeyedDataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

/**
 * paging方案
 */
public class PagingNetworkViewModel2 extends ViewModel {
    private int mPage;
    private LiveData<PagedList<ArticleBean.DataBean.Article>> mPageData;

    //数据源
    private DataSource mDataSource;

    //数据源工厂
    private DataSource.Factory mFactory = new DataSource.Factory() {
        @NonNull
        @Override
        public DataSource create() {
            if (mDataSource == null || mDataSource.isInvalid()) {
                //下拉刷新调用mDataSource.invalidate()，这时需要创建一个新的数据源
                mDataSource = createDataSource();
            }
            return mDataSource;
        }
    };

    //创建数据源
    private DataSource createDataSource() {
        return new ItemKeyedDataSource<Integer, ArticleBean.DataBean.Article>() {

            //paging首次加载数据
            @Override
            public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<ArticleBean.DataBean.Article> callback) {
                //这3个load方法在子线程中执行，同步获取网络数据即可
                callback.onResult(Api.getArticle(String.valueOf(mPage++)));
            }

            //paging加载更多数据，在滑动到配置好的位置时，自动触发
            @Override
            public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<ArticleBean.DataBean.Article> callback) {
                callback.onResult(Api.getArticle(String.valueOf(mPage++)));
            }

            @Override
            public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<ArticleBean.DataBean.Article> callback) {
                //向前加载，忽略即可
            }

            @NonNull
            @Override
            public Integer getKey(@NonNull ArticleBean.DataBean.Article item) {
                return item.getId();
            }
        };
    }

    public LiveData<PagedList<ArticleBean.DataBean.Article>> getPageData() {
        if (null == mPageData) {
            PagedList.Config config = new PagedList.Config.Builder()
                    .setPageSize(20)  //分页大小
                    .setInitialLoadSizeHint(20)  //首次加载大小
                    .setPrefetchDistance(10)  //预加载距离：还剩10个就要滑到底了，就进行预加载
                    .build();
            mPageData = new LivePagedListBuilder(mFactory, config).build();
        }
        return mPageData;
    }

    //下拉刷新
    public void refresh() {
        mPage = 0;
        mDataSource.invalidate();
    }
}
