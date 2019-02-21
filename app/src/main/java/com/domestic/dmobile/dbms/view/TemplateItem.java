package com.domestic.dmobile.dbms.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.domestic.dmobile.R;

public class TemplateItem extends FrameLayout {

    //TODO Set Textview Template
    TextView tvTempNo;

    public TemplateItem(@NonNull Context context) {
        super(context);
        initInflate();
        initInstance();
    }

    public TemplateItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstance();
    }

    public TemplateItem(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstance();
    }

    @TargetApi(21)
    public TemplateItem(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstance();
    }

    //region #----- Initial ----------
    private void initInflate() {
        //TODO Set initInflate item Template
        //inflate(getContext(), R.layout.item_sms, this);
    }

    private void initInstance() {
        //TODO Set initInstance Template
        //tvTempNo = findViewById(R.id.tvConNo);
    }
    //endregion


    //region #----- Listener ----------
    private void initListener() {
        setOnListener();
        //TODO Set initListener Template
    }
    //---------------------------------
    private void setOnListener() {
        //TODO Set setonListener Template
    }
    //endregion


    //region #----- MyMethod ----------
    //TODO MyMethod
    public void setTempNo(int num) {
        //TODO Set setText Template
    }
    //endregion
}
