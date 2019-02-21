package com.domestic.dmobile.dbms.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class BroadcastSms {
    @SerializedName("getUsername")        private int sms010PK;
    @SerializedName("CON_NO")           private String conNo;
    @SerializedName("SMS_NOTE")         private String smsNote;
    @SerializedName("SMS_TIME")         private Date smsTime;
    @SerializedName("SENDER")           private int sender;
    @SerializedName("SENDER_TYPE")      private String senderType;
}
