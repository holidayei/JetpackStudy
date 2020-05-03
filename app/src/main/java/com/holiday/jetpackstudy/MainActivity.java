package com.holiday.jetpackstudy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.holiday.jetpackstudy.databinding.ActivityMainBinding;
import com.holiday.jetpackstudy.databinding.DataBindingActivity;
import com.holiday.jetpackstudy.lifecycle.LifecycleActivity;
import com.holiday.jetpackstudy.model.User;
import com.holiday.jetpackstudy.navigation.NavigationActivity;
import com.holiday.jetpackstudy.paging.PagingActivity;
import com.holiday.jetpackstudy.room.RoomActivity;
import com.holiday.jetpackstudy.viewmodel_livedata.ViewModelActivity;
import com.holiday.jetpackstudy.workmanager.WorkManagerActivity;

/**
 * an app for study jetpack
 * <p>
 * google官网：https://developer.android.google.cn/jetpack
 * B站：https://www.bilibili.com/video/BV1w4411t7UQ
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.tvName.setTextColor(0xff666666);
        binding.setUser(new User("用户名", "用户密码"));
    }

    public void gotoDataBinding(View view) {
        startActivity(getIntentByClass(DataBindingActivity.class));
    }

    private Intent getIntentByClass(Class cls) {
        return new Intent(this, cls);
    }

    public void gotoLifecycles(View view) {
        startActivity(getIntentByClass(LifecycleActivity.class));
    }

    public void gotoViewModel_LiveData(View view) {
        startActivity(getIntentByClass(ViewModelActivity.class));
    }

    public void gotoWorkManager(View view) {
        startActivity(getIntentByClass(WorkManagerActivity.class));
    }

    public void gotoNavigation(View view) {
        startActivity(getIntentByClass(NavigationActivity.class));
    }

    public void gotoPaging(View view) {
        startActivity(getIntentByClass(PagingActivity.class));
    }

    public void gotoRoom(View view) {
        startActivity(getIntentByClass(RoomActivity.class));
    }
}
