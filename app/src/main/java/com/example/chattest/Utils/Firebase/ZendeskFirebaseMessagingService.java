package com.example.chattest.Utils.Firebase;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.chattest.Controller.MainActivity;
import com.example.chattest.R;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.zendesk.util.StringUtils;
import com.zopim.android.sdk.api.ZopimChatApi;
import com.zopim.android.sdk.model.PushData;

import zendesk.core.Zendesk;
import zendesk.support.Support;
import zendesk.support.request.RequestActivity;


public class ZendeskFirebaseMessagingService extends FirebaseMessagingService {

    private static final String LOG_TAG = "PushListenerService";

    private static final int NOTIFICATION_ID = (int) System.currentTimeMillis();

    private static final String NOTIFICATION_CHANNEL_ID = "ChatSampleAppChannelId";
    private static final String NOTIFICATION_CHANNEL_NAME = "ChatSampleAppChannelId";

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.e("NEW_TOKEN",s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        NotificationManager manager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);

        if (manager == null) {
            Log.d(LOG_TAG, "Notification manager not found");
            return;
        }

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_launcher);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                    NOTIFICATION_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);

            channel.enableVibration(true);
            channel.setVibrationPattern(new long[] {100, 200, 100, 200});

            builder.setChannelId(NOTIFICATION_CHANNEL_ID);
            manager.createNotificationChannel(channel);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            builder.setPriority(Notification.PRIORITY_HIGH);
        }

        PushData pushData = PushData.getChatNotification(remoteMessage.getData());

        switch(pushData.getType()) {
            case END:
                builder.setContentTitle("Chat ended");
                builder.setContentText("The chat has ended!");
                break;
            case MESSAGE:
                builder.setContentTitle("Chat message");
                builder.setContentText("New chat message!");
                break;
            case NOT_CHAT:
                // ignore
                break;
        }

        manager.notify(NOTIFICATION_ID, builder.build());

        // IMPORTANT! forward the notification data to the SDK
        ZopimChatApi.onMessageReceived(pushData);
    }
}