package com.domestic.dmobile.util;

import android.support.v4.app.FragmentManager;
import android.util.Log;

public class FragmentUtil {

    private static FragmentUtil instance;

    public FragmentUtil() {
    }

    public static FragmentUtil newInstance() {
        if (instance == null) instance = new FragmentUtil();
        return instance;
    }

    public void removeBackStack(FragmentManager fragment) {
        //Count Fragment On
        int backStackEntry = fragment.getBackStackEntryCount();
        if (backStackEntry > 0) {
            for (int i = 0; i < backStackEntry; i++) fragment.popBackStackImmediate();
        }

        //Remove Fragment All
        if (fragment.getFragments() != null && fragment.getFragments().size() > 0) {
            for (int i = 0; i < fragment.getFragments().size(); i++) {
                if (fragment.getFragments().get(i) != null)
                    fragment.beginTransaction().remove(fragment.getFragments().get(i)).commit();
            }
        }
    }
}
