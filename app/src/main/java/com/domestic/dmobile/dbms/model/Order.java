package com.domestic.dmobile.dbms.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Order {
    @SerializedName("code")         private int code;
    @SerializedName("message")      private String message;
    @SerializedName("data")         private Data data;

    //region ----- Get, Set ----------
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
    //endregion

    public class Data {
        @SerializedName("Status")           private String status;
        @SerializedName("Code")             private String code;
        @SerializedName("Message")          private String message;
        @SerializedName("TransactionId")    private String transactionId;
        @SerializedName("Amount")           private String amount;
        @SerializedName("OrderNo")          private String orderNo;
        @SerializedName("CustomerId")       private String customerId;
        @SerializedName("ChannelCode")      private String channelCode;
        @SerializedName("ReturnUrl")        private String returnUrl;
        @SerializedName("PaymentUrl")       private String paymentUrl;
        @SerializedName("IpAddress")        private String ipAddress;
        @SerializedName("Token")            private String token;
        @SerializedName("CreatedDate")      private String createdDate;
        @SerializedName("ExpiredDate")      private String expiredDate;


        //region ----- Get, Set ----------

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getTransactionId() {
            return transactionId;
        }

        public void setTransactionId(String transactionId) {
            this.transactionId = transactionId;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getChannelCode() {
            return channelCode;
        }

        public void setChannelCode(String channelCode) {
            this.channelCode = channelCode;
        }

        public String getReturnUrl() {
            return returnUrl;
        }

        public void setReturnUrl(String returnUrl) {
            this.returnUrl = returnUrl;
        }

        public String getPaymentUrl() {
            return paymentUrl;
        }

        public void setPaymentUrl(String paymentUrl) {
            this.paymentUrl = paymentUrl;
        }

        public String getIpAddress() {
            return ipAddress;
        }

        public void setIpAddress(String ipAddress) {
            this.ipAddress = ipAddress;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getExpiredDate() {
            return expiredDate;
        }

        public void setExpiredDate(String expiredDate) {
            this.expiredDate = expiredDate;
        }

        //endregion
    }

    public class Model {

        //region ----- Get, Set ----------
        //endregion
    }
}
