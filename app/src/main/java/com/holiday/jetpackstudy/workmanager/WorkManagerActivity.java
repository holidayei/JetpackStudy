package com.holiday.jetpackstudy.workmanager;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.holiday.jetpackstudy.R;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 使用：https://zhuanlan.zhihu.com/p/78599394
 * <p>
 * 封装，不同版本系统有不同实现。
 * ：在运行 API 23 及以上级别的设备上使用 JobScheduler；
 * ：在运行 API 14-22 的设备上结合使用 BroadcastReceiver 和 AlarmManager
 * 数据库存储，关机重启仍可运行。可能受厂商限制而失效。
 * 可以指定一些场景执行如低电量。
 * 需要注意的是：周期性任务的间隔时间不能小于15分钟，小于15分钟的话内部也会改成15分钟。
 */
public class WorkManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_manager);

        Data inputData = new Data.Builder().putString("input_data", "输入数据").build();

        //约束条件
        Constraints constraints = new Constraints.Builder()
                .setRequiresCharging(true) //充电状态
                .setRequiredNetworkType(NetworkType.CONNECTED)  //网络连接
                .setRequiresBatteryNotLow(true)  //非低电量
                .build();

        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(MyWorker.class)
                .setConstraints(constraints)
                .setInitialDelay(2, TimeUnit.SECONDS)
                .addTag("MyWorker")
                .setInputData(inputData)
                //setBackoffCriteria 回退策略
                .build();

        OneTimeWorkRequest workRequest2 = new OneTimeWorkRequest.Builder(MyWorker.class)
                .setConstraints(constraints)
                .setInitialDelay(2, TimeUnit.SECONDS)
                .addTag("MyWorker2")
                .setInputData(inputData)
                .build();

//        PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(MyWorker.class, 15, TimeUnit.MINUTES)
//                .setConstraints(constraints)
//                .addTag("MyWorker")
//                .build();

        //任务链，先后关系
        WorkManager.getInstance(this).beginWith(workRequest2).then(workRequest).enqueue();

        //可以监听工作状态
        LiveData<List<WorkInfo>> liveData = WorkManager.getInstance(this).getWorkInfosByTagLiveData("MyWorker");
        liveData.observe(this, new Observer<List<WorkInfo>>() {
            @Override
            public void onChanged(List<WorkInfo> workInfos) {
//                for (WorkInfo info : workInfos) {
//                    QrLog.e(info.toString());
//                }
            }
        });
    }
}
