package com.example.chattest.Utils.Globals;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.chattest.R;
import com.example.chattest.Utils.Storage.AppStorage;
import com.zopim.android.sdk.api.ZopimChat;

import zendesk.answerbot.AnswerBot;
import zendesk.core.Zendesk;
import zendesk.support.Support;


public class Globals extends Application {

    public final static String LOG_TAG = "RTD";

    private AppStorage storage;

    public static AppStorage getStorage(@Nullable Context context) {
        if (context != null && context.getApplicationContext() instanceof Globals) {
            return ((Globals) context.getApplicationContext()).storage;
        }

        throw new IllegalArgumentException("Can't find global Application");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        storage = new AppStorage(this);

        // Picasso.with(this).setLoggingEnabled(true);
        // Enable logging in Support and Chat SDK
        //Logger.setLoggable(true);

        // Init Support SDK
        Zendesk.INSTANCE.init(this, getResources().getString(R.string.zd_url),
                getResources().getString(R.string.zd_appid),
                getResources().getString(R.string.zd_oauth));
        Support.INSTANCE.init(Zendesk.INSTANCE);
        AnswerBot.INSTANCE.init(Zendesk.INSTANCE, Support.INSTANCE);

        // Init Chat SDK
        if ("replace_me_chat_account_id".equals(getString(R.string.zopim_account_id))) {
            Log.w(LOG_TAG, "==============================================================================================================");
            Log.w(LOG_TAG, "Zopim chat is not connected to an account, if you wish to try chat please add your Zopim accountId to 'zd.xml'");
            Log.w(LOG_TAG, "==============================================================================================================");
        }
        ZopimChat.init(getString(R.string.zopim_account_id));
    }

}