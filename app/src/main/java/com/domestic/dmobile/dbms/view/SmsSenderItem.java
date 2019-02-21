package com.domestic.dmobile.dbms.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.domestic.dmobile.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SmsSenderItem extends FrameLayout {

    private TextView tvSmsNote, tvSmsTime;

    public SmsSenderItem(@NonNull Context context) {
        super(context);
        initInflate();
        initInstance();
    }

    public SmsSenderItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstance();
    }

    public SmsSenderItem(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstance();
    }

    @TargetApi(21)
    public SmsSenderItem(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstance();
    }


    //region #----- Initial ----------
    private void initInflate() {
        inflate(getContext(), R.layout.item_sms_sender, this);
    }

    private void initInstance() {
        tvSmsNote = findViewById(R.id.tvSmsNote);
        tvSmsTime = findViewById(R.id.tvSmsTime);

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

    public void setTvSmsNote(String str) {
        tvSmsNote.setText(str.replace("\r","\n"));
    }

    public void setTvSmsTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        tvSmsTime.setText(dateFormat.format(date));
    }

    //endregion
}
