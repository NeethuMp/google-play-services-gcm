package com.example.neethu.gcmservice;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by neethu on 29/2/16.
 */
public class MyInstanceIDListenerService extends InstanceIDListenerService {
//    private static final String TAG="MyInstanceIDLS";

    @Override
    public void onTokenRefresh() {
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
    }
}
