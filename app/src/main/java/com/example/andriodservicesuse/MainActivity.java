package com.example.andriodservicesuse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static  final String TAG=MainActivity.class.getSimpleName();
    TextView textView;
    Button startButton,stopButton;
    int count=0;

    private Intent serviceIntent;

    private boolean mStopLoop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("service_demo_tag","MainActivity"+Thread.currentThread().getId());

        textView=findViewById(R.id.textView);
        startButton=findViewById(R.id.button);
        stopButton=findViewById(R.id.button2);

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

    }
}