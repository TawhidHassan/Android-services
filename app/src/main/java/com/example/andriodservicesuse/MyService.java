package com.example.andriodservicesuse;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Random;

public class MyService extends Service {

    private int mRandomNumber;
    private boolean mIsRandomGenaratoron=false;
    private  final int MIN=0;
    private  final int MAX=100;

    class  MyServiceBinder extends Binder{
        public MyService getService(){
            return MyService.this;
        }
    }

    private IBinder iBinder=new MyServiceBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("service_demo_tag","IBinder "+Thread.currentThread().getId());
        return iBinder;
    }

    @Override
    public void onDestroy() {
        Log.i("service_demo_tag","service get destroy "+Thread.currentThread().getId());
        stopRandomNumberGenarator();
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("service_demo_tag","on startcommand ,threes id "+Thread.currentThread().getId());
//        stopSelf();
        mIsRandomGenaratoron=true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("service_demo_tag","fun call ");
             startRandomNumberGenerator();
            }
        }).start();
        return START_STICKY;
    }

    private void startRandomNumberGenerator(){
        while (mIsRandomGenaratoron){
            try{
                Thread.sleep(1000);
                if(mIsRandomGenaratoron){
                    mRandomNumber=new Random().nextInt(MAX)+MIN;
                    Log.i("service_demo_tag","Thread Id:"+Thread.currentThread().getId()+" ,Random num,ber"+ mRandomNumber);
                }
            }catch (InterruptedException e)
            {
                Log.i("service_demo_tag","Thread Interrupted");
            }
        }
    }

    private void  stopRandomNumberGenarator(){
        mIsRandomGenaratoron=false;
    }

    private int getRandomNumber(){
        return mRandomNumber;
    }

}
