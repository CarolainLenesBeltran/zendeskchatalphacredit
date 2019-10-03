package com.example.chattest.Utils.Firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;


public class ZendeskFirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        //Log.d(LOG_TAG, "Firebase token updated");
        Push.registerWithZendesk();
    }

}