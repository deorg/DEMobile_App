package com.domestic.dmobile.dbms.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.domestic.dmobile.R;
import com.domestic.dmobile.dbms.model.Customer;
import com.domestic.dmobile.dbms.model.Sms;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SmsItem extends FrameLayout {

    TextView tvConNo, tvSmsNote, tvSmsTime, tvSms010PK, tvDayEEE, tvDayd, tvDayGroup;

    public SmsItem(@NonNull Context context) {
        super(context);
        initInflate();
        initInstance();
    }

    public SmsItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstance();
    }

    public SmsItem(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstance();
    }

    @TargetApi(21)
    public SmsItem(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstance();
    }

    //region #----- Initial ----------
    private void initInflate() {
        inflate(getContext(), R.layout.item_sms, this);
    }

    private void initInstance() {
        tvConNo = findViewById(R.id.tvConNo);
        tvSmsNote = findViewById(R.id.tvSmsNote);
        tvSmsTime = findViewById(R.id.tvSmsTime);
        tvSms010PK = findViewById(R.id.tvSms010PK);
        tvDayEEE = findViewById(R.id.tvDayEEE);
        tvDayd = findViewById(R.id.tvDayd);
        tvDayGroup = findViewById(R.id.tvDayGroup);

        initListener();
    }
    //endregion


    //region #----- Listener ----------
    private void initListener() {
        setOnListener();
        //TODO initListener
    }
    //---------------------------------
    private void setOnListener() {
        //TODO setonListener
    }
    //endregion


    //region #----- MyMethod ----------
    public void setTvConNo(String str) {
        tvConNo.setText(str);
    }

    public void setTvSmsNote(String str) {
        tvSmsNote.setText(str.replace("\r","\n"));
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void setTvSmsTime(Date date) {
        //SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yy hh:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        tvSmsTime.setText(dateFormat.format(date));
        tvDayEEE.setText(new SimpleDateFormat("EEE").format(date).replace(".",""));
        tvDayd.setText(new SimpleDateFormat("d").format(date));

//        String DayOfWeek = new SimpleDateFormat("F").format(date);
//        switch (DayOfWeek) {
//            case "1": tvDay.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.background_circle)); break;
//            case "2": tvDay.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.background_circle)); break;
//            case "3": tvDay.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.background_circle)); break;
//            case "4": tvDay.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.background_circle)); break;
//            case "5": tvDay.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.background_circle)); break;
//            case "6": tvDay.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.background_circle)); break;
//            case "7": tvDay.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.background_circle)); break;
//            default:
//        }

    }

    public void setTvSms010PK(int num) {
        tvSms010PK.setText(String.valueOf(num));
    }

    public void setTvDayGroup(Date date) {
        tvDayGroup.setText(new SimpleDateFormat("d MMM yy").format(date));
        tvDayGroup.setVisibility(VISIBLE);
    }

    public void setTvDayGroupVisible() {
        tvDayGroup.setVisibility(GONE);
    }
    //endregion
}
