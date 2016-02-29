package com.example.neethu.gcmservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String TAG = "mainactivity";
    BroadcastReceiver mRegistrationBroadcastReceiver;
    ProgressBar mRegistrationProgressBar;
    TextView mInformationTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRegistrationProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mInformationTextview = (TextView) findViewById(R.id.informationTextview);
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                mRegistrationProgressBar.setVisibility(ProgressBar.GONE);
                SharedPreferences sharedPreferences =
                        PreferenceManager.getDefaultSharedPreferences(context);
                boolean sentToken = sharedPreferences
                        .getBoolean(QuickstartPreferences.SEND_TOKEN_TO_SERVER, false);
                if (sentToken) {
                    mInformationTextview
                            .setText(getString(R.string.gcm_send_message));
                } else {
                    mInformationTextview
                            .setText(getString(R.string.token_error_message));
                }
            }
        };
        if (checkPlayServices()) {
            // Start IntentService to register this application with GCM.
            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
        }
    }
    @Override
    protected void onResume() {
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(QuickstartPreferences.REGISTRATION_COMPLETE));
        super.onResume();
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }
    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability=GoogleApiAvailability.getInstance();
        int resultCode=apiAvailability.isGooglePlayServicesAvailable(this);
        if(resultCode!= ConnectionResult.SUCCESS)
        {
            if(apiAvailability.isUserResolvableError(resultCode))
            {
                apiAvailability.getErrorDialog(this,resultCode,PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            }else{
                Log.i(TAG, "This device is not supported.");
                finish();

            }
            return  false;
        }
        return  true;
    }

}

