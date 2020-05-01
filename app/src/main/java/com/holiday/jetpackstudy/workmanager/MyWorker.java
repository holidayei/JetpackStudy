package com.holiday.jetpackstudy.workmanager;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.holiday.jetpackstudy.utils.QrLog;

public class MyWorker extends Worker {
    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        QrLog.e(getTags() + " doWork = " + getInputData().getString("input_data") + "," + Thread.currentThread());
        return Result.success();
    }
}
