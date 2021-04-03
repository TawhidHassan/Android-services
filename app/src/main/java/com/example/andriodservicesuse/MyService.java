package com.example.andriodservicesuse;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onDestroy() {
        Log.i("service_demo_tag","service get destroy "+Thread.currentThread().getId());
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("service_demo_tag","on startcommand ,threes id "+Thread.currentThread().getId());
//        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }
}
