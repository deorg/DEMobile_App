package com.domestic.dmobile.dbms.model;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Sms {
    @SerializedName("code")         private int code;
    @SerializedName("message")      private String message;
    @SerializedName("data")         private List<Data> data;

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

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    //endregion

    public class Data {
        @SerializedName("SMS010_PK")        private int sms010PK;
        @SerializedName("CUST_NO")          private int custNo;
        @SerializedName("CON_NO")           private String conNo;
        @SerializedName("SMS_NOTE")         private String smsNote;
        @SerializedName("SMS_TIME")         private Date smsTime;
        @SerializedName("SENDER")           private int sender;
        @SerializedName("SENDER_TYPE")      private String senderType;
        @SerializedName("READ_STATUS")      private String readStatus;

        //region ----- Get, Set ----------
        public int getSms010PK() {
            return sms010PK;
        }

        public void setSms010PK(int sms010PK) {
            this.sms010PK = sms010PK;
        }

        public int getCustNo() {
            return custNo;
        }

        public void setCustNo(int custNo) {
            this.custNo = custNo;
        }

        public String getConNo() {
            return conNo;
        }

        public void setConNo(String conNo) {
            this.conNo = conNo;
        }

        public String getSmsNote() {
            return smsNote;
        }

        public void setSmsNote(String smsNote) {
            this.smsNote = smsNote;
        }

        public Date getSmsTime() {
            return smsTime;
        }

        public void setSmsTime(Date smsTime) {
            this.smsTime = smsTime;
        }

        public int getSender() {
            return sender;
        }

        public void setSender(int sender) {
            this.sender = sender;
        }

        public String getSenderType() {
            return senderType;
        }

        public void setSenderType(String senderType) {
            this.senderType = senderType;
        }

        public String getReadStatus() {
            return readStatus;
        }

        public void setReadStatus(String readStatus) {
            this.readStatus = readStatus;
        }
        //endregion
    }

    public class MarkRead {
        @SerializedName("SMS010_PK")        private int sms010PK;
        @SerializedName("CUST_NO")          private int custNo;

        //region ----- Get, Set ----------
        public int getSms010PK() {
            return sms010PK;
        }

        public void setSms010PK(int sms010PK) {
            this.sms010PK = sms010PK;
        }

        public int getCustNo() {
            return custNo;
        }

        public void setCustNo(int custNo) {
            this.custNo = custNo;
        }
        //endregion
    }

    public class SendSms {
        @SerializedName("CUST_NO")          private int custNo;
        @SerializedName("MESSAGE")          private String message;

        //region ----- Get, Set ----------

        public int getCustNo() {
            return custNo;
        }

        public void setCustNo(int custNo) {
            this.custNo = custNo;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        //endregion
    }

    public class ReceiveSms {
        @SerializedName("code")         private int code;
        @SerializedName("message")      private String message;
        @SerializedName("data")         private Response data;

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

        public Response getData() {
            return data;
        }

        public void setData(Response data) {
            this.data = data;
        }

        //endregion

        public class Response {
            @SerializedName("SMS010_PK")        private int sms010PK;
            @SerializedName("CUST_NO")          private int custNo;
            @SerializedName("CON_NO")           private String conNo;
            @SerializedName("SMS_NOTE")         private String smsNote;
            @SerializedName("SMS_TIME")         private Date smsTime;
            @SerializedName("SMS010_REF")       private int sms010Ref;
            @SerializedName("SENDER")           private int sender;
            @SerializedName("SENDER_TYPE")      private String senderType;
            @SerializedName("READ_STATUS")      private String readStatus;

            //region ----- Get, Set ----------

            public int getSms010PK() {
                return sms010PK;
            }

            public void setSms010PK(int sms010PK) {
                this.sms010PK = sms010PK;
            }

            public int getCustNo() {
                return custNo;
            }

            public void setCustNo(int custNo) {
                this.custNo = custNo;
            }

            public String getConNo() {
                return conNo;
            }

            public void setConNo(String conNo) {
                this.conNo = conNo;
            }

            public String getSmsNote() {
                return smsNote;
            }

            public void setSmsNote(String smsNote) {
                this.smsNote = smsNote;
            }

            public Date getSmsTime() {
                return smsTime;
            }

            public void setSmsTime(Date smsTime) {
                this.smsTime = smsTime;
            }

            public int getSms010Ref() {
                return sms010Ref;
            }

            public void setSms010Ref(int sms010Ref) {
                this.sms010Ref = sms010Ref;
            }

            public int getSender() {
                return sender;
            }

            public void setSender(int sender) {
                this.sender = sender;
            }

            public String getSenderType() {
                return senderType;
            }

            public void setSenderType(String senderType) {
                this.senderType = senderType;
            }

            public String getReadStatus() {
                return readStatus;
            }

            public void setReadStatus(String readStatus) {
                this.readStatus = readStatus;
            }

            //endregion
        }

    }

}



