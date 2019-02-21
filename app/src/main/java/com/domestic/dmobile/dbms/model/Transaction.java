package com.domestic.dmobile.dbms.model;

import com.google.gson.annotations.SerializedName;

public class Transaction {
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
        @SerializedName("Code")                     private int code;
        @SerializedName("Status")                   private String status;
        @SerializedName("Message")                  private String message;
        @SerializedName("TransactionId")            private int transactionId;
        @SerializedName("Amount")                   private int amount;
        @SerializedName("OrderNo")                  private String orderNo;
        @SerializedName("CustomerId")               private String customerId;
        @SerializedName("BankCode")                 private String bankCode;
        @SerializedName("PaymentDate")              private String paymentDate;
        @SerializedName("PaymentStatus")            private int paymentStatus;
        @SerializedName("BankRefCode")              private String bankRefCode;
        @SerializedName("MerchantResponseStatus")   private int merchantResponseStatus;
        @SerializedName("MerchantResponseMessage")  private String merchantResponseMessage;
        @SerializedName("CurrentDate")              private String currentDate;
        @SerializedName("CurrentTime")              private String currentTime;
        @SerializedName("PaymentDescription")       private String paymentDescription;

        //region ----- Get, Set ----------

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getTransactionId() {
            return transactionId;
        }

        public void setTransactionId(int transactionId) {
            this.transactionId = transactionId;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
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

        public String getBankCode() {
            return bankCode;
        }

        public void setBankCode(String bankCode) {
            this.bankCode = bankCode;
        }

        public String getPaymentDate() {
            return paymentDate;
        }

        public void setPaymentDate(String paymentDate) {
            this.paymentDate = paymentDate;
        }

        public int getPaymentStatus() {
            return paymentStatus;
        }

        public void setPaymentStatus(int paymentStatus) {
            this.paymentStatus = paymentStatus;
        }

        public String getBankRefCode() {
            return bankRefCode;
        }

        public void setBankRefCode(String bankRefCode) {
            this.bankRefCode = bankRefCode;
        }

        public int getMerchantResponseStatus() {
            return merchantResponseStatus;
        }

        public void setMerchantResponseStatus(int merchantResponseStatus) {
            this.merchantResponseStatus = merchantResponseStatus;
        }

        public String getMerchantResponseMessage() {
            return merchantResponseMessage;
        }

        public void setMerchantResponseMessage(String merchantResponseMessage) {
            this.merchantResponseMessage = merchantResponseMessage;
        }

        public String getCurrentDate() {
            return currentDate;
        }

        public void setCurrentDate(String currentDate) {
            this.currentDate = currentDate;
        }

        public String getCurrentTime() {
            return currentTime;
        }

        public void setCurrentTime(String currentTime) {
            this.currentTime = currentTime;
        }

        public String getPaymentDescription() {
            return paymentDescription;
        }

        public void setPaymentDescription(String paymentDescription) {
            this.paymentDescription = paymentDescription;
        }

        //endregion
    }

    public class Model {
        //region ----- Get, Set ----------
        //endregion
    }
}
