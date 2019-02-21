package com.domestic.dmobile.manager.hub;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.domestic.dmobile.MainApplication;
import com.domestic.dmobile.R;
import com.domestic.dmobile.activity.MainActivity;
import com.domestic.dmobile.dbms.model.ReceiveSms;
import com.domestic.dmobile.dbms.model.Sms;
import com.domestic.dmobile.dbms.model.SmsNotif;
import com.domestic.dmobile.fragment.SmsFragment;
import com.domestic.dmobile.manager.http.HttpManager;

import java.util.concurrent.ExecutionException;

import microsoft.aspnet.signalr.client.Platform;
import microsoft.aspnet.signalr.client.SignalRFuture;
import microsoft.aspnet.signalr.client.http.android.AndroidPlatformComponent;
import microsoft.aspnet.signalr.client.hubs.HubConnection;
import microsoft.aspnet.signalr.client.hubs.HubProxy;
import microsoft.aspnet.signalr.client.hubs.SubscriptionHandler1;
import microsoft.aspnet.signalr.client.transport.ClientTransport;
import microsoft.aspnet.signalr.client.transport.ServerSentEventsTransport;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignalR {

    private HubConnection hubConnection;
    private HubProxy hubProxy;
    private Handler mHandler = new Handler();
    private Context mContext;
    private final static String serverUrl = "http://35.247.128.114/signalr";

    public void connect(Context context) {
        Log.v("ake", "SignalR Connect");
        mContext = context;
        Platform.loadPlatformComponent(new AndroidPlatformComponent());
        hubConnection = new HubConnection(serverUrl);
        hubConnection.connected(new Runnable() {
            @Override
            public void run() {
                Log.v("ake", "Hub Connect");
            }
        });
        hubConnection.closed(new Runnable() {
            @Override
            public void run() {
                Log.v("ake", "Hub Closed");
            }
        });
        hubConnection.reconnected(new Runnable() {
            @Override
            public void run() {
                Log.v("ake", "Hub ReConnect");
            }
        });



        hubProxy = hubConnection.createHubProxy("chatHub");
        ClientTransport clientTransport = new ServerSentEventsTransport((hubConnection.getLogger()));
        SignalRFuture<Void> signalRFuture = hubConnection.start(clientTransport);

        onListenerBroadcast();
        onListenerSmsPersonal();

        try {
            signalRFuture.get();
            signalRFuture.onCancelled(new Runnable() {
                @Override
                public void run() {
                    Log.v("ake", "Hub Cancel");
                }
            });
        } catch (InterruptedException | ExecutionException e) {
            Log.v("ake", "signalRFuture Error");
            return;
        }

        String deviceID = Settings.Secure.getString(mContext.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        hubProxy.invoke("registerContext", new Object[]{deviceID});
    }

    private void onListenerBroadcast() {
        hubProxy.on("broadcast", new SubscriptionHandler1<Message>() {
            @Override
            public void run(final Message msg) {
                sendToNotification(msg.message);
            }
        }, Message.class);
    }

    private void onListenerSmsPersonal() {
//        Log.v("ake", "onListenerSmsPersonal Start");
//
//        hubProxy.on("sms", new SubscriptionHandler1<SmsNotif>() {
//            @Override
//            public void run(SmsNotif smsNotif) {
//                Log.v("ake", "sms smsNotif");
//            }
//        }, SmsNotif.class);
//
////

        hubProxy.on("sms", new SubscriptionHandler1<SmsNotif>() {
            @Override
            public void run(final SmsNotif dao) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.v("ake", "isFragmentSmsActive : " + MainApplication.isFragmentSmsActive());
                        if (MainApplication.isFragmentSmsActive()) {
                            Log.v("ake", "isFragmentSmsActive is true : ");
                            if (dao.getSmsNote().equals("ข้อความใหม่")) {
                                Call<Sms> call = HttpManager.getInstance(HttpManager.MODE_CUSTOMER)
                                        .getService()
                                        .getSms(dao.getCustNo());
                                call.enqueue(new Callback<Sms>() {
                                    @Override
                                    public void onResponse(Call<Sms> call, Response<Sms> response) {
                                        if (response.isSuccessful()) {
                                            for (Sms.Data d: response.body().getData()) {
                                                if (d.getSms010PK() == dao.getSms010PK()){
                                                    Log.v("ake", "1.เปลี่ยนข้อความใหม่ = " + d.getSmsNote());
                                                    dao.setSmsNote(d.getSmsNote());
                                                    Log.v("ake", "2.เปลี่ยนข้อความใหม่ = " + dao.getSmsNote());
                                                }
                                            }
                                            SmsFragment.adapter.addDao(dao);
                                            SmsFragment.adapter.notifyDataSetChanged();
                                            SmsFragment.listItem.setSelection(SmsFragment.adapter.getCount());
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Sms> call, Throwable t) {
                                    }
                                });
                            } else {
                                SmsFragment.adapter.addDao(dao);
                                SmsFragment.adapter.notifyDataSetChanged();
                                SmsFragment.listItem.setSelection(SmsFragment.adapter.getCount());
                            }

                        } else {
                            Log.v("ake", "isFragmentSmsActive is false : ");
                            sendToNotification(dao.getSmsNote());
                        }
                    }
                });
            }
        }, SmsNotif.class);
    }

    private void sendToNotification(String message) {
        Log.v("ake", "SignalR sendToNotif");
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext, "notify_001");
        Intent intent = new Intent(mContext, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
        //bigText.setBigContentTitle("Today's Bible Verse");
        //bigText.setSummaryText("Text in detail");

        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setSmallIcon(R.mipmap.logo_company);
        //mBuilder.setContentTitle("Your Title");
        mBuilder.setContentText(message);
        mBuilder.setPriority(Notification.DEFAULT_LIGHTS);
        //mBuilder.setStyle(bigText);
        mBuilder.setAutoCancel(true);

        NotificationManager mNotificationManager =
                (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "SMS";
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_HIGH);
            mNotificationManager.createNotificationChannel(channel);
            mBuilder.setChannelId(channelId);
        }

        mNotificationManager.notify(100, mBuilder.build());

    }



    private class Message {
        public String username = "Name";
        public String message = "Messsage";

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }


}
