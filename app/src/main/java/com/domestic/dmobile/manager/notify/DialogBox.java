package com.domestic.dmobile.manager.notify;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.domestic.dmobile.R;
import com.domestic.dmobile.manager.Contextor;

public class DialogBox {

    private static DialogInterface.OnClickListener NeutralOnClick, PositiveOnClick, NegativeOnClick;
    public static String answerYN;
    private static Context mContext;

    public DialogBox(Context context) {
        mContext = context;
    }

    public static void info(String title, String message) {
        Log.v("ake","DialogBox - info");

        onListenerClick();
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext)
                .setCancelable(false)
                .setIcon(R.mipmap.icon_button_sms) //TODO Change Icon info
                .setTitle(title)
                .setMessage(message)
                .setNeutralButton("OK", NeutralOnClick);
        builder.create();
        builder.show();
    }

    public static void caution(String title, String message) {
        Log.v("ake","DialogBox - caution");

        onListenerClick();
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext)
                .setCancelable(false)
                .setIcon(R.mipmap.icon_sms_mobile) //TODO Change Icon caution
                .setTitle(title)
                .setMessage(message)
                .setNeutralButton("OK", NeutralOnClick);
        builder.create();
        builder.show();
    }

    public static void askYN(String title, String message) {
        Log.v("ake","DialogBox - askYN");

        onListenerClickYN();
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext)
                .setCancelable(false)
                .setIcon(R.mipmap.icon_button_sms) //TODO Change Icon ask
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Yes", PositiveOnClick)
                .setNegativeButton("No", NegativeOnClick);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private static void onListenerClick() {
        NeutralOnClick = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };
    }

    private static void onListenerClickYN() {
        PositiveOnClick = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                answerYN = "Yes";
                dialog.dismiss();
            }
        };

        NegativeOnClick = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                answerYN = "No";
                dialog.dismiss();
            }
        };
    }
}
