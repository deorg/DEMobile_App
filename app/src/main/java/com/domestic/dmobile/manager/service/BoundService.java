package com.domestic.dmobile.manager.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Chronometer;

import com.domestic.dmobile.manager.hub.SignalR;


public class BoundService extends Service {

    private IBinder mBinder = new MyBinder();
    private Chronometer mChronometer;
    private SignalR signalR = new SignalR();

    @Override
    public void onCreate() {
        super.onCreate();
        mChronometer = new Chronometer(this);
        mChronometer.setBase(SystemClock.elapsedRealtime());
        mChronometer.start();

        //TODO ให้ Start หลัง Register แล้ว และทุกครั้งที่ ConnectionID หลุด
        //TODO ให้ยิงไปขอ Unread ทุกครั้งที่ SignalR Connect
        signalR.connect(getApplicationContext());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mChronometer.stop();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    public class MyBinder extends Binder {
        public BoundService getService() {
            return BoundService.this;
        }
    }
}
