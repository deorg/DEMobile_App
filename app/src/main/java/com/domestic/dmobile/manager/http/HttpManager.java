package com.domestic.dmobile.manager.http;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {

    private static HttpManager instance;
    private ApiService service;
    private static String baseUrl = "http://35.247.128.114/api/customer/";
    private static String intUrl = "http://35.247.128.114/api/";
    private static String urlCustomer = "customer/";
    private static String urlAuthen = "authen/";
    private static String urlPayment = "payment/";

    public static final int MODE_AUTHEN = 0;
    public static final int MODE_CUSTOMER = 1;
    public static final int MODE_PAYMENT = 2;

    public static HttpManager getInstance(int mode) {
        //--- Init Url
        if (mode == 0) baseUrl = intUrl + urlAuthen;
        if (mode == 1) baseUrl = intUrl + urlCustomer;
        if (mode == 2) baseUrl = intUrl + urlPayment;
//        switch (mode) {
//            case MODE_CUSTOMER: baseUrl = intUrl + urlCustomer; break;
//            case MODE_PAYMENT: baseUrl = intUrl + urlPayment; break;
//            default: baseUrl = intUrl + urlRegister;
//        }


        //--- Init Http
        //if (instance == null) instance = new HttpManager();
        instance = new HttpManager();
        return instance;
    }

    private HttpManager() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        service = retrofit.create(ApiService.class);
    }

    public ApiService getService() {
        return service;
    }
}
