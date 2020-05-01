package com.holiday.jetpackstudy.lifecycle;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.holiday.jetpackstudy.R;

/**
 * 使用：https://zhuanlan.zhihu.com/p/88905213
 * 原理：https://www.jianshu.com/p/b1208012b268
 * <p>
 * LiveData 与 ViewModel 的 lifecycle 也依赖于 Lifecycle 框架。
 * 创建过程，act优先于观察者；销毁过程，观察者优先于act，毕竟要等观察者处理完了如释放资源，act才能销毁。
 * 优点：解耦，避免act臃肿；由于解耦，实现了模块化和可移植。
 * 缺点：暂无。
 */
public class LifecycleActivity extends AppCompatActivity {
    private static final String TAG = "Lifecycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        Log.e(TAG, "act onCreate");

        //在Android Support Library 26.1.0 及其之后的版本，Activity和Fragment已经默认实现了LifecycleOwner接口
        //当前act作为被观察者LifecycleOwner，为其添加了两个观察者LifecycleObserver
        getLifecycle().addObserver(new MyObserver());
        getLifecycle().addObserver(new MyObserver2());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "act onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "act onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "act onDestroy");
    }
}
