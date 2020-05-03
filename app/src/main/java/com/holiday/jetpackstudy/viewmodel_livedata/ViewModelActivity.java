package com.holiday.jetpackstudy.viewmodel_livedata;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;

import com.holiday.jetpackstudy.R;
import com.holiday.jetpackstudy.base.BaseActivity;
import com.holiday.jetpackstudy.databinding.ActivityViewModelBinding;
import com.holiday.jetpackstudy.utils.QrLog;

/**
 * LiveData原理：https://juejin.im/post/5c25753af265da61561f5335
 * ViewModel原理：https://juejin.im/post/5c047fd3e51d45666017ff86
 * <p>
 * ViewModel只负责业务不关注UI，生命长于宿主，宿主销毁自己也会自动销毁并回调onCleared。
 * fragment与act通信，可以让多个fragment共享act的vm，数据共享。
 * 还可以维护一个app级别的vm，处理全局数据。
 * 原则：ViewModel 不要引用视图、Lifecycle 或可能存储对 Activity 上下文的引用的任何类。
 */
public class ViewModelActivity extends BaseActivity {
    private CommonViewModel commonViewModel;
    private ActivityViewModelBinding mBinding;
    private MutableLiveData<String> mLiveData = new MutableLiveData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_view_model);
        commonViewModel = ViewModelProviders.of(this).get(CommonViewModel.class);
        commonViewModel.text = "文案";
        mBinding.tvText.setText(commonViewModel.text);

        //旋转屏幕重建act后，vm还是同一个实例
//        QrLog.e(String.valueOf(commonViewModel.hashCode()));

        mLiveData.observe(this, s -> {
            //只有处于活跃状态：onStart、onResume、onPause才会收到回调
            QrLog.e("----------------- observe = " + s + "," + getLifecycle().getCurrentState());
        });
        //观察所有生命周期，需要手动removeObserver
        mLiveData.observeForever(s -> {
//            QrLog.e("----------------- observeForever = " + s + "," + getLifecycle().getCurrentState());
        });
        mLiveData.setValue("onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        mLiveData.setValue("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLiveData.setValue("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLiveData.setValue("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        mLiveData.setValue("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLiveData.setValue("onDestroy");
    }
}
