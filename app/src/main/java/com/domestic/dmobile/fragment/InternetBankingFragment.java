package com.domestic.dmobile.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.domestic.dmobile.R;
import com.domestic.dmobile.activity.MainActivity;
import com.domestic.dmobile.dbms.model.Order;
import com.domestic.dmobile.dbms.model.Transaction;
import com.domestic.dmobile.dbms.model.User;
import com.domestic.dmobile.manager.http.HttpManager;
import com.domestic.dmobile.util.FragmentUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InternetBankingFragment extends Fragment {

    private WebView webView;
    //private WebViewClient webViewClient;
    private static String paymentUrl, orderNo;
    private static Order order;
    private TextView tvTimer;
    private int time = 0;
    private CountDownTimer countTimer;
    private User mUser;

    public static InternetBankingFragment newInstance(Order order) {
        order = order;
        paymentUrl = order.getData().getPaymentUrl();
        orderNo = order.getData().getOrderNo();
        InternetBankingFragment fragment = new InternetBankingFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //TODO Set FragmentTemplate
        View view = inflater.inflate(R.layout.fragment_internet_banking, container, false);
        initInstance(view);
        initListener();
        return view;

    }

    //region #----- Initial ----------
    private void initInstance(View view) {
        tvTimer = view.findViewById(R.id.tvTimer);
        webView = view.findViewById(R.id.webView);
        //webViewClient = new WebViewClient();
        webView.getSettings().setJavaScriptEnabled(true);
        //webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.setInitialScale(1);




        Log.v("ake", "InternetBankingFragment : " + paymentUrl);
    }
    //endregion


    //region #----- Listener ----------
    private void initListener() {
        setOnListener();

        //TODO Set initListener Template
        webView.loadUrl(paymentUrl);
    }
    //---------------------------------
    private void setOnListener() {
        countTimer = new CountDownTimer(15000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //String text = tvTimer.getText().toString().replaceAll("[\\D]", "");
                time = time + 1;
                tvTimer.setText("รอการยืนยันจากเซิฟเวอร์ " + String.valueOf(time) + " วินาที");

                Log.v("ake", "Timer : onTick");
            }

            @Override
            public void onFinish() {
                Log.v("ake", "Timer : onFinish");
                tvTimer.setVisibility(View.GONE);
            }
        };


        //TODO Set setOnListener Template

        webView.setWebViewClient(new WebViewClient() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Log.v("ake", "shouldOverrideUrlLoading : view /" + view.getUrl());
                Log.v("ake", "shouldOverrideUrlLoading : request /" + request.getUrl().getPath().toString());

                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                Log.v("ake", "onPageFinished : view /" + view.getUrl());
                Log.v("ake", "onPageFinished : url /" + url);

                if (url.equals("http://35.247.128.114/Success")) {
                    tvTimer.setVisibility(View.VISIBLE);
                    time = 0;
                    countTimer.start();

                    Log.v("ake", "onPageFinished : URL Success - Go to Transection (Order No. " + orderNo);
                    try {
                        Call<Transaction> call = HttpManager.getInstance(HttpManager.MODE_PAYMENT).getService().getTransStatus(Integer.parseInt(orderNo));
                        call.enqueue(new TransLoadCallback());
                    } catch (Exception e) {
                        Log.v("ake", "onPageFinished : URL Success - Go to Transection Error - " + e.getMessage());
                    }

                } else Log.v("ake", "onPageFinished : URL No Success - No Transection");
            }

            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Log.v("ake", "onReceivedError : " + error.getDescription().toString());
            }
        });

    }
    //endregion


    //region #----- MyMethod ----------
    //TODO Set MyMethod Template






    private class TransLoadCallback implements Callback<Transaction> {

        @Override
        public void onResponse(Call<Transaction> call, Response<Transaction> response) {
            if (response.isSuccessful()) {
                Transaction dao = response.body();

                Log.v("ake", "InternetBankingFragment onResponse : Success - " + dao.getData().getPaymentStatus() + "(" + dao.getData().getPaymentDescription() + ")");

                Toast.makeText(getContext(), "สำเร็จ", Toast.LENGTH_LONG).show();
                switch (dao.getData().getPaymentStatus()) {
                    case 0: {
                        countTimer.cancel();
                        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                        alertDialog.setTitle("ผลการชำระ");
                        alertDialog.setMessage("ชำระสำเร็จ");
                        alertDialog.setCancelable(false);
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        getActivity().getSupportFragmentManager().beginTransaction()
                                                .replace(R.id.contentContrainer, ContractFragment.newInstance())
                                                .commit();
                                        dialog.dismiss();
                                        FragmentUtil.newInstance().removeBackStack(getActivity().getSupportFragmentManager());
                                    }
                                });
                        alertDialog.show();
                        break;
                    }
                    default: break;

                }
            } else Log.v("ake", "InternetBankingFragment onResponse : false / " + response.message());

        }

        @Override
        public void onFailure(Call<Transaction> call, Throwable t) {

        }
    }
    //endregion
}
