package com.holiday.jetpackstudy.paging;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.holiday.jetpackstudy.room.dao.UserDao;
import com.holiday.jetpackstudy.room.entity.User;

public class PagingViewModel extends ViewModel {
    private UserDao mUserDao;
    private LiveData<PagedList<User>> mLiveData;

    public void setUserDao(UserDao userDao) {
        mUserDao = userDao;
    }

    public LiveData<PagedList<User>> getLiveData() {
        if (null == mLiveData && null != mUserDao) {
            DataSource.Factory<Integer, User> factory = mUserDao.queryUsersLive();
            PagedList.Config config = new PagedList.Config.Builder()
                    .setPageSize(15)              // 分页加载的数量
                    .setInitialLoadSizeHint(30)   // 初次加载的数量
                    .setPrefetchDistance(10)      // 预取数据的距离
                    .setEnablePlaceholders(false) // 是否启用占位符（本地数据比较合适，因为远程数据是未知的）
                    .build();
            mLiveData = new LivePagedListBuilder<>(factory, config).build();
        }
        return mLiveData;
    }
}
