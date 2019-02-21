package com.domestic.dmobile.manager.http;


import com.domestic.dmobile.dbms.model.Bank;
import com.domestic.dmobile.dbms.model.Contract;
import com.domestic.dmobile.dbms.model.Customer;
import com.domestic.dmobile.dbms.model.Order;
import com.domestic.dmobile.dbms.model.Sms.ReceiveSms;
import com.domestic.dmobile.dbms.model.Request;
import com.domestic.dmobile.dbms.model.Sms;
import com.domestic.dmobile.dbms.model.Transaction;
import com.domestic.dmobile.dbms.model.User.Register;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("register")
    Call<Register.Response> Register(@Body Register register);

    @POST("sendmessage")
    Call<ReceiveSms> postSendMessage(@Body Sms.SendSms message);

    @GET("identify")
    Call<Customer> Identify(@Query("serial_sim") String phoneNo,
                            @Query("deviceId") String deviceId);

    @GET("profile")
    Call<Customer> getCustomer(@Query("id") int id);

    @GET("sms")
    Call<Sms> getSms(@Query("id") int id);

    @POST("sms/marktoread")
    Call<Sms.MarkRead> postMarkRead(@Body Sms.MarkRead markRead);

    @GET("contract")
    Call<Contract> getContract(@Query("id") int id);


    //region #----- MyMethod ----------
    @GET("getchannel")
    Call<Bank> getBank();

    @POST("newpayment2")
    Call<Order> postOrder(@Body Request req);

    @GET("getstatus")
    Call<Transaction> getTransStatus(@Query("order_no") int orderNo);

    //endregion

}
