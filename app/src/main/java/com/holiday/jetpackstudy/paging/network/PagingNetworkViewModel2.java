package com.holiday.jetpackstudy.paging.network;

import androidx.annotation.NonNull;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.ItemKeyedDataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.holiday.jetpackstudy.utils.QrLog;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * paging方案
 */
public class PagingNetworkViewModel2 extends ViewModel {
    private int mPage;
    private LiveData<PagedList<ArticleBean.DataBean.Article>> mPageData;
    private ItemKeyedDataSource.LoadCallback mLoadCallback;//记录下加载更多的LoadCallback
    private AtomicBoolean mLoadingMore = new AtomicBoolean(false);//标记位，由预加载、加载更多两个地方触发
    private MutableLiveData<Boolean> mFinishLoadMore = new MutableLiveData<>(false);//通知activity隐藏加载更多UI

    //数据源
    private ItemKeyedDataSource mDataSource;

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
    private ItemKeyedDataSource createDataSource() {
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
                if (mLoadingMore.get()) {
                    return;
                }
                mLoadingMore.set(true);
                List<ArticleBean.DataBean.Article> list = Api.getArticle(String.valueOf(mPage));
                if (null == list || list.isEmpty()) {
                    mLoadCallback = callback;
                } else {
                    callback.onResult(list);
                    mPage++;
                    mLoadCallback = null;
                }
                mLoadingMore.set(false);
                mFinishLoadMore.postValue(true);
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

    //fix:关闭网络或某次预加载失败，导致恢复网络后也无法进行预加载了，所以还需开启加载更多。官方Demo的做法是在列表底部放了一个重试按钮触发loadAfter的
    public void loadMore() {
        if (mDataSource != null && !mDataSource.isInvalid() && null != mLoadCallback) {
            //手动切回子线程
            ArchTaskExecutor.getIOThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    QrLog.e("触发了加载更多");
                    mDataSource.loadAfter(null, mLoadCallback);
                }
            });
        }
    }

    public MutableLiveData<Boolean> getFinishLoadMore() {
        return mFinishLoadMore;
    }
}
