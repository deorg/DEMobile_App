package com.domestic.dmobile.dbms.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.domestic.dmobile.R;


public class BankItem extends FrameLayout {

    //TODO Set Textview Template
    private TextView tvChannelId, tvChannelName;
    private ImageView ivBank;

    public BankItem(@NonNull Context context) {
        super(context);
        initInflate();
        initInstance();
    }

    public BankItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstance();
    }

    public BankItem(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstance();
    }

    @TargetApi(21)
    public BankItem(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstance();
    }

    //region #----- Initial ----------
    private void initInflate() {
        inflate(getContext(), R.layout.item_bank, this);
    }

    private void initInstance() {
        ivBank = findViewById(R.id.ivBank);
        tvChannelId = findViewById(R.id.tvChannelId);
        tvChannelName = findViewById(R.id.tvChannelName);
        initListener();
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

//        ivBank.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.v("ake", "ivBank Click : " + tvChannelId.getText().toString());
//            }
//        });
    }
    //endregion


    //region #----- MyMethod ----------
    //TODO MyMethod
    public void setIbtnbank(String strUrl) {
//        Uri uri = Uri.parse(strUrl);
//        Log.v("ake", "strUrl : " + strUrl);
//        Log.v("ake", "uri : " + uri.toString());
//        //if (uri != null) ibtnbank.setImageURI(uri);

//        Bitmap bitmap = BitmapFactory.decodeFile(strUrl);
//        ibtnBank.setImageBitmap(bitmap);

//        try {
//            InputStream stream = (InputStream) new URL(strUrl).getContent();
//            Drawable drawable = Drawable.createFromStream(stream, null);
//            ibtnBank.setImageDrawable(drawable);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Glide.with(getContext())
                //.setDefaultRequestOptions(requestOptions)
                .load(strUrl)
                //.apply(R.drawable.ic_account)
                .into(ivBank);
//                .placeholder(R.drawable.ic_device)
//                .error(R.drawable.ic_account)
//                .diskCacheStrategy(DiskCacheStrategy.ALL) //แสดงผลมากกว่า 1 ขนาดจาก Url เดียวกัน
//                .into(ivBank);
    }

    public void setTvChannelId(String str) {
        tvChannelId.setText(str);
    }

    public void setTvChannelName(String str) {
        tvChannelName.setText(str);
    }
    //endregion
}
