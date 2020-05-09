package com.holiday.jetpackstudy.paging;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.holiday.jetpackstudy.MyApp;
import com.holiday.jetpackstudy.R;
import com.holiday.jetpackstudy.databinding.ActivityPagingBinding;
import com.holiday.jetpackstudy.paging.network.PagingNetworkActivity;
import com.holiday.jetpackstudy.paging.network.PagingNetworkActivity2;
import com.holiday.jetpackstudy.room.dao.UserDao;
import com.holiday.jetpackstudy.room.entity.User;
import com.holiday.jetpackstudy.utils.QrLog;

import java.util.List;

/**
 * 原理：https://juejin.im/post/5c53ad9e6fb9a049eb3c5cfd
 * <p>
 * 对数据进行diff，然后局部刷新，本质还是走适配器的notifyItemRangeXXX，所以省去了业务层进行局部刷新的逻辑，只需操作数据即可
 * diff会在子线程进行
 */
public class PagingActivity extends AppCompatActivity {
    private PagingViewModel mViewModel;
    private ActivityPagingBinding mBinding;
    //其内创建了AsyncListDiffer的实例，以便在后台线程中使用DiffUtil计算新旧数据集的差异
    private MyListAdapter mListAdapter;
    private UserDao mUserDao = MyApp.app.getAppDatabase().userDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_paging);
        mViewModel = ViewModelProviders.of(this).get(PagingViewModel.class);
        mViewModel.setUserDao(mUserDao);
        mViewModel.getLiveData().observe(this, new Observer<PagedList<User>>() {
            @Override
            public void onChanged(PagedList<User> users) {
                //内部会进行diff
                mListAdapter.submitList(users);
                QrLog.e("观察数据变化 = " + users.size());
            }
        });
        mListAdapter = new MyListAdapter();
        mBinding.rvUser.setAdapter(mListAdapter);
        mBinding.rvUser.setLayoutManager(new LinearLayoutManager(this));
        mBinding.setHandler(new Handler());
    }

    public class Handler {
        public void addItem() {
            User user = new User();
            user.setName(String.valueOf(System.currentTimeMillis() / 1000));
            mUserDao.insertUsers(user);
        }

        public void updateUsers() {
            User user = new User();
            user.setName(String.valueOf(System.currentTimeMillis() / 1000));
            user.setId(1);
            mUserDao.updateUsers(user);
        }

        public void deleteUsers() {
            List<User> users = mUserDao.queryUsers();
            mUserDao.deleteUsers(users.get(users.size() - 1));
        }

        public void networkDemo() {
            Intent intent = new Intent(PagingActivity.this, PagingNetworkActivity.class);
            startActivity(intent);
        }

        public void networkDemo2() {
            Intent intent = new Intent(PagingActivity.this, PagingNetworkActivity2.class);
            startActivity(intent);
        }
    }
}
