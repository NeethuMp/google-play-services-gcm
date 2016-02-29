package com.example.neethu.gcmservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
        mInformationTextview= (TextView) findViewById(R.id.informationTextview);
        mREgistrationBroadcastReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                mRegistrationProgressBar.setVisibility(View.GONE);
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                Boolean sendToken = sharedPreferences.getBoolean(QuickstartPreferences.SEND_TOKEN_TO_SERVER, false);
                if (sendToken) {
                    mInformationTextview.setText(R.string.gcm_send_message);
                } else {
                    mInformationTextview.setText(R.string.token_error_message);
                }
                if(checkPlayServices){
                    Intent intent=new Intent(MainActivity.this,RegistrationIntent.class);
                    startActivity(intent);
                }
            }
        };
    }
}
