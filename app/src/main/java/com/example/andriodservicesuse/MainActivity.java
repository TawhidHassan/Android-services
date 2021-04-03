package com.example.andriodservicesuse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static  final String TAG=MainActivity.class.getSimpleName();
    TextView textView;
    Button startButton,stopButton,bindserviceButton,unBindServiceButton,getRandomNumber;
    int count=0;

    private Intent serviceIntent;
    private MyService myService;
    private boolean isServiceBound;
    private ServiceConnection serviceConnection;
    private boolean mStopLoop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("service_demo_tag","MainActivity"+Thread.currentThread().getId());

        textView=findViewById(R.id.textView);
        startButton=findViewById(R.id.button);
        stopButton=findViewById(R.id.button2);
        bindserviceButton=findViewById(R.id.button3);
        unBindServiceButton=findViewById(R.id.button4);
        getRandomNumber=findViewById(R.id.button5);

        serviceIntent=new Intent(getApplicationContext(),MyService.class);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(serviceIntent);
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(serviceIntent);
            }
        });


        bindserviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (serviceConnection==null){
                    serviceConnection=new ServiceConnection() {
                        @Override
                        public void onServiceConnected(ComponentName name, IBinder iBinder) {
                            MyService.MyServiceBinder myServiceBinder=(MyService.MyServiceBinder)iBinder;
                            myService=myServiceBinder.getService();
                            isServiceBound=true;
                        }

                        @Override
                        public void onServiceDisconnected(ComponentName name) {
                                isServiceBound=false;
                        }
                    };
                }
                bindService(serviceIntent,serviceConnection, Context.BIND_AUTO_CREATE);
            }
        });

        unBindServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isServiceBound){
                    unbindService(serviceConnection);
                    isServiceBound=false;
                }
            }
        });

        getRandomNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isServiceBound){
                    textView.setText("Random number: "+myService.getRandomNumber());
                }else
                {
                    textView.setText("Service not bound");
                }
            }
        });

    }
}