package com.domestic.dmobile.manager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings.Secure;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.domestic.dmobile.dbms.model.User;


public class Permissions extends AppCompatActivity {

    private User mUser;
    private static Permissions instance;
    private static Context mContext;

    public static Permissions getInstance() {
        if (instance == null) instance = new Permissions();
        return instance;
    }

    public void check(Activity activity, Context context) {
        mContext = context;
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED)
        {

            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.READ_PHONE_STATE},
                    0);
        } else getDevice(context);
    }

    public void onResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        for (int grantResult : grantResults) {
            if (grantResult == -1) ((Activity) mContext).finish();
        }
        getDevice(mContext);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    @SuppressLint("MissingPermission")
    private void getDevice(Context context) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);

        String deviceId = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
        String phoneNo = manager.getLine1Number();
        String simPhoneNo = manager.getLine1Number();
        String Operator = manager.getSimOperatorName();
        String Brand = Build.MANUFACTURER.toUpperCase();
        String Model = Build.MODEL;
        String simSerial = manager.getSimSerialNumber();
        int apiVersion = Build.VERSION.SDK_INT;

//        if (phoneNo.equals("")) phoneNo = manager.getSubscriberId();

//        if (phoneNo.equals("")) {
//            List<SubscriptionInfo> subscription = SubscriptionManager.from(context).getActiveSubscriptionInfoList();
//            for (int i = 0; i < subscription.size(); i++) {
//                SubscriptionInfo info = subscription.get(i);
//                Log.v("ake", "number " + info.getNumber());
//                Log.v("ake", "network name : " + info.getCarrierName());
//                Log.v("ake", "country iso " + info.getCountryIso());
//                if (info.getNumber().equals("")) phoneNo = "null";
//                else phoneNo = info.getNumber();
//            }
//        }

//        if (simPhoneNo.equals("")) simPhoneNo = "N/A";

        mUser.setPhoneNo(phoneNo);
        mUser.setSimPhoneNo(simPhoneNo);
        mUser.setDeviceId(deviceId);
        mUser.setSimSerial(simSerial);
        mUser.setOperator(Operator);
        mUser.setBrand(Brand);
        mUser.setModel(Model);
        mUser.setApiVersion(apiVersion);

        Log.v("ake", "phoneNo : " + mUser.getPhoneNo());
        Log.v("ake", "simPhoneNo : " + mUser.getSimPhoneNo());
        Log.v("ake", "deviceId : " + mUser.getDeviceId());
        Log.v("ake", "simSerial : " + mUser.getSimSerial());
        Log.v("ake", "simOperator : " + mUser.getOperator());
        Log.v("ake", "Brand : " + mUser.getBrand());
        Log.v("ake", "Model : " + mUser.getModel());
        Log.v("ake", "apiVersion : " + mUser.getApiVersion());
    }

}
