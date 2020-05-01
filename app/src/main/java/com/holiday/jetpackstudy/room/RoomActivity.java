package com.holiday.jetpackstudy.room;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.holiday.jetpackstudy.MyApp;
import com.holiday.jetpackstudy.R;
import com.holiday.jetpackstudy.databinding.ActivityRoomBinding;
import com.holiday.jetpackstudy.room.dao.UserDao;
import com.holiday.jetpackstudy.room.entity.User;
import com.holiday.jetpackstudy.utils.QrToast;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用：https://www.jianshu.com/p/3e358eb9ac43
 */
public class RoomActivity extends AppCompatActivity {
    ActivityRoomBinding mBinding;
    UserDao mUserDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_room);
        RoomHandler roomHandler = new RoomHandler();
        mBinding.setRoomHandler(roomHandler);
        mUserDao = MyApp.app.getAppDatabase().userDao();
    }

    public class RoomHandler {
        public void insertUsers() {
            User user = new User();
            user.setName(String.valueOf(System.currentTimeMillis() / 1000));
            mUserDao.insertUsers(user);
            showDb();
        }

        public void updateUsers() {
            User user = new User();
            user.setName(String.valueOf(System.currentTimeMillis() / 1000));
            user.setId(1);
            mUserDao.updateUsers(user);
            showDb();
        }

        public void deleteUsers() {
            List<User> users = mUserDao.queryUsers();
            mUserDao.deleteUsers(users.get(users.size() - 1));
            showDb();
        }

        public void queryUsers() {
            showDb();
        }

        public void queryUserById() {
            List<User> users = mUserDao.queryUserById(1);
            showList(users);
        }

        public void queryUserBetweenId() {
            List<User> users = mUserDao.queryUserBetweenId(1, 3);
            showList(users);
        }

        public void queryUserInIds() {
            List<Integer> ids = new ArrayList<>();
            ids.add(1);
            ids.add(3);
            LiveData<List<User>> listLiveData = mUserDao.queryUserInIds(ids);
            listLiveData.observe(RoomActivity.this, new Observer<List<User>>() {
                @Override
                public void onChanged(List<User> users) {
                    QrToast.show("监听到room返回的liveData发生变化");
//                    showList(users);
                }
            });
        }

        private void showDb() {
            List<User> users = mUserDao.queryUsers();
            StringBuilder sb = new StringBuilder();
            for (User user : users) {
                sb.append(user.getId()).append(" , ");
                sb.append(user.getName()).append("\n");
            }
            mBinding.tvDb.setText(sb.toString());
        }

        private void showList(List<User> users) {
            StringBuilder sb = new StringBuilder();
            for (User user : users) {
                sb.append(user.getId()).append(" , ");
                sb.append(user.getName()).append("\n");
            }
            mBinding.tvDb.setText(sb.toString());
        }
    }
}
