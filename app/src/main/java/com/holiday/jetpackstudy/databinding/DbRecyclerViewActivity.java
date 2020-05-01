package com.holiday.jetpackstudy.databinding;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.holiday.jetpackstudy.R;
import com.holiday.jetpackstudy.model.User;
import com.holiday.jetpackstudy.utils.DataUtil;

import java.util.ArrayList;
import java.util.List;

public class DbRecyclerViewActivity extends AppCompatActivity {
    private DbUserAdapter mDbUserAdapter;
    private ActivityDbRecyclerViewBinding mBinding;
    private ObservableArrayList<User> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_db_recycler_view);
        mList = new ObservableArrayList<>();
        for (int i = 1; i < 99; i++) {
            User user = new User("name" + i, "pwd" + i);
            user.setImg(DataUtil.getImgByIdx(i));
            mList.add(user);
        }
        mDbUserAdapter = new DbUserAdapter(mList);
        mBinding.rvUser.setAdapter(mDbUserAdapter);
        mBinding.rvUser.setLayoutManager(new LinearLayoutManager(this));

        //观察数据变化 -> 收到变化通知 -> 自动处理adapter更新UI
        mList.addOnListChangedCallback(new DynamicChangeCallback(mDbUserAdapter));
    }

    //以下的方法只需更新数据源，即可自动更新UI
    public void addItem(View view) {
        User user = new User("addItem", String.valueOf(System.currentTimeMillis() / 1000));
        mList.add(1, user);
    }

    public void addItemList(View view) {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            User user = new User("addItemList " + i, String.valueOf(System.currentTimeMillis() / 1000));
            userList.add(user);
        }
        mList.addAll(1, userList);
    }

    public void removeItem(View view) {
        mList.remove(1);
    }

    public void updateItem(View view) {
        mList.get(1).setName("updateItem");
        mList.set(1, mList.get(1));
    }
}
