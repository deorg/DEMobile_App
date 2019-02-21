package com.domestic.dmobile;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.domestic.dmobile.dbms.model.Customer;
import com.domestic.dmobile.dbms.model.User;
import com.domestic.dmobile.manager.Contextor;
import com.domestic.dmobile.manager.http.HttpManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainApplication extends Application {
    private static boolean fragmentSmsActive;
    public static boolean appFirstRun;

    @Override
    public void onCreate() {
        super.onCreate();
        Contextor.getInstance().init(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }







    //region #----- App Active ----------
    public static boolean isFragmentSmsActive() {
        return fragmentSmsActive;
    }

    public static void fragmentSmsResumed() {
        fragmentSmsActive = true;
    }

    public static void fragmentSmsPaused() {
        fragmentSmsActive = false;
    }
    //endregion



}