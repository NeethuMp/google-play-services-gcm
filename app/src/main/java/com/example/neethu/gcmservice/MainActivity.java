package com.example.neethu.gcmservice;

import android.content.BroadcastReceiver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private  static  final int PLAY_SERVICES_RESOLUTION_REQUEST=9000;
    private static final String TAG="mainactivity";
    BroadcastReceiver mREgistrationBroadcastReceiver;
    ProgressBar mRegistrationProgressBar;
    TextView mInformationTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRegistrationProgressBar= (ProgressBar) findViewById(R.id.progressBar);
        mInformationTextview= (TextView) findViewById(R.id.textView);
    }
}
