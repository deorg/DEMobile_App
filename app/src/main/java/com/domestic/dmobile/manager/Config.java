package com.domestic.dmobile.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Config {
    private static boolean fragmentSmsActive;
    private static boolean appFirstRun;

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

    public static boolean isAppFirstRun() {
        return appFirstRun;
    }

    public static void setAppFirstRun() {
        SharedPreferences sharedPreferences = Contextor.getInstance().getContext().getSharedPreferences("PREFS_NAME", 0);
        appFirstRun = sharedPreferences.getBoolean("FIRST_RUN", true);
    }

}
