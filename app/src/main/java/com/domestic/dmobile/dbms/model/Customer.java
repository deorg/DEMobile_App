package com.domestic.dmobile.dbms.model;

import com.google.gson.annotations.SerializedName;

public class Customer {
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
        @SerializedName("CUST_NO")          private int custNo;
        @SerializedName("CUST_NAME")        private String custName;
        @SerializedName("CITIZEN_NO")       private String citizenNo;
        @SerializedName("TEL")              private String phoneNo;
        @SerializedName("PERMIT")           private String permit;
        @SerializedName("DEVICE_STATUS")    private String deviceStatus;

        //region ----- Get, Set ----------

        public int getCustNo() {
            return custNo;
        }

        public void setCustNo(int custNo) {
            this.custNo = custNo;
        }

        public String getCustName() {
            return custName;
        }

        public void setCustName(String custName) {
            this.custName = custName;
        }

        public String getCitizenNo() {
            return citizenNo;
        }

        public void setCitizenNo(String citizenNo) {
            this.citizenNo = citizenNo;
        }

        public String getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
        }

        public String getPermit() {
            return permit;
        }

        public void setPermit(String permit) {
            this.permit = permit;
        }

        public String getDeviceStatus() {
            return deviceStatus;
        }

        public void setDeviceStatus(String deviceStatus) {
            this.deviceStatus = deviceStatus;
        }
//endregion
    }
}


