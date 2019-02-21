package com.domestic.dmobile.dbms.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.domestic.dmobile.R;
import com.domestic.dmobile.activity.MainActivity;
import com.domestic.dmobile.dbms.model.Contract;
import com.domestic.dmobile.fragment.PaymentFragment;
import com.domestic.dmobile.util.FragmentUtil;

import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Converter;

public class ContractItem extends FrameLayout {

    private LinearLayout layoutContract;
    private TextView tvConNo, tvCustNo, tvConDate, tvTotAmt, tvPayAmt, tvPeriod, tvDiscAmt, tvBalAmt;

    private FragmentActivity myContext;
    private LinearLayout.OnClickListener layoutListener;

    private DecimalFormat decimalFormat = new DecimalFormat("#,###");

    public ContractItem(@NonNull Context context) {
        super(context);

        myContext = (FragmentActivity) context;
        initInflate();
        initInstance();
        initListener();
    }

    public ContractItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstance();
        initListener();
    }

    public ContractItem(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstance();
        initListener();
    }

    @TargetApi(21)
    public ContractItem(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstance();
        initListener();
    }

    //region #----- Initial ----------
    private void initInflate() {
        inflate(getContext(), R.layout.item_contract, this);
    }

    private void initInstance() {
        layoutContract = findViewById(R.id.layoutContract);
        tvConNo = findViewById(R.id.tvConNo);
        tvCustNo = findViewById(R.id.tvCustNo);
        tvConDate = findViewById(R.id.tvConDate);
        tvTotAmt = findViewById(R.id.tvTotAmt);
        tvPayAmt = findViewById(R.id.tvPayAmt);
        tvPeriod = findViewById(R.id.tvPeriod);
        tvDiscAmt = findViewById(R.id.tvDiscAmt);
        tvBalAmt = findViewById(R.id.tvBalAmt);
    }
    //endregion


    //region #----- Listener ----------
    private void initListener() {
        setOnListener();

        //TODO Set initListener Template
        layoutContract.setOnClickListener(layoutListener);
    }
    //---------------------------------
    private void setOnListener() {
        //TODO Set setonListener Template
        layoutListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Contract.Data dao = new Contract().new Data();

                String CustNo = tvCustNo.getText().toString().replaceAll("[\\D]", "");
                String ConDate = tvConDate.getText().toString();
                String TotAmt = tvTotAmt.getText().toString().replaceAll("[\\D]", "");
                String PayAmt = tvPayAmt.getText().toString().replaceAll("[\\D]", "");
                String Period = tvPeriod.getText().toString().replaceAll("[\\D]", "");
                String DiscAmt = tvDiscAmt.getText().toString().replaceAll("[\\D]", "");
                String BalAmt = tvBalAmt.getText().toString().replaceAll("[\\D]", "");

                dao.setConNo(tvConNo.getText().toString());
                dao.setCustNo(CustNo == null? 0 : Integer.parseInt(CustNo));
                dao.setConDate(ConDate == null? null : dateFormat.parse(ConDate, new ParsePosition(0)));
                dao.setTotAmt(TotAmt == null? 0 : Integer.parseInt(TotAmt));
                dao.setPayAmt(PayAmt == null? 0 : Integer.parseInt(PayAmt));
                dao.setPeriod(Period == null? 0 : Integer.parseInt(Period));
                dao.setDiscAmt(DiscAmt == null? 0 : Integer.parseInt(DiscAmt));
                dao.setBalAmt(BalAmt == null? 0 : Integer.parseInt(BalAmt));

                myContext.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentContrainer, PaymentFragment.newInstance(dao))
                        .addToBackStack(null)
                        .commit();
            }
        };
    }
    //endregion


    //region #----- MyMethod ----------
    public void setTvConNo(String str) {
        tvConNo.setText(str);
    }

    public void setTvCustNo(int num) {
        tvCustNo.setText(String.valueOf(num));
    }

    public void setTvConDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        tvConDate.setText(dateFormat.format(date));
    }

    public void setTvTotAmt(int num) {
        tvTotAmt.setText(decimalFormat.format(num));
    }

    public void setTvPayAmt(int num) {
        tvPayAmt.setText(decimalFormat.format(num));
    }

    public void setTvPeriod(int num) {
        tvPeriod.setText(decimalFormat.format(num));
    }

    public void setTvBalAmt(int num) {
        tvBalAmt.setText(decimalFormat.format(num));
    }

    public void setTvDiscAmt(int num) {
        tvDiscAmt.setText(decimalFormat.format(num));
    }
    //endregion
}
