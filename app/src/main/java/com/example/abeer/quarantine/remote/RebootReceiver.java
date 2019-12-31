package com.example.abeer.quarantine.remote;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.abeer.quarantine.remote.WorkBack;

import java.util.concurrent.TimeUnit;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

public class RebootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Constraints constraints=new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.METERED) //fire on mobile data
                .setRequiredNetworkType(NetworkType.CONNECTED)//fire on wifi can delete it if u dont want to fire on wifi
                .setRequiresCharging(true)
                .setRequiresCharging(false)
                .setRequiresBatteryNotLow(false)
                .setRequiresBatteryNotLow(true)
                .build();

        OneTimeWorkRequest.Builder builder=new OneTimeWorkRequest.Builder(WorkBack.class).setInitialDelay(2,TimeUnit.MINUTES);
      OneTimeWorkRequest request = builder.setConstraints(constraints).build();
        WorkManager.getInstance().enqueue(request);
    }
}
