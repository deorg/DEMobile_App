package com.domestic.dmobile.dbms.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Contract {
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
        @SerializedName("CON_NO")       private String conNo;
        @SerializedName("CON_DATE")     private Date conDate;
        @SerializedName("CUST_NO")      private int custNo;
        @SerializedName("TOT_AMT")      private int totAmt;
        @SerializedName("PAY_AMT")      private int payAmt;
        @SerializedName("PERIOD")       private int period;
        @SerializedName("BAL_AMT")      private int balAmt;
        @SerializedName("DISC_AMT")     private int discAmt;

        //region ----- Get, Set ----------
        public String getConNo() {
            return conNo;
        }

        public void setConNo(String conNo) {
            this.conNo = conNo;
        }

        public Date getConDate() {
            return conDate;
        }

        public void setConDate(Date conDate) {
            this.conDate = conDate;
        }

        public int getCustNo() {
            return custNo;
        }

        public void setCustNo(int custNo) {
            this.custNo = custNo;
        }

        public int getTotAmt() {
            return totAmt;
        }

        public void setTotAmt(int totAmt) {
            this.totAmt = totAmt;
        }

        public int getPayAmt() {
            return payAmt;
        }

        public void setPayAmt(int payAmt) {
            this.payAmt = payAmt;
        }

        public int getPeriod() {
            return period;
        }

        public void setPeriod(int period) {
            this.period = period;
        }

        public int getBalAmt() {
            return balAmt;
        }

        public void setBalAmt(int balAmt) {
            this.balAmt = balAmt;
        }

        public int getDiscAmt() {
            return discAmt;
        }

        public void setDiscAmt(int discAmt) {
            this.discAmt = discAmt;
        }
        //endregion
    }

    public class Model {
        public static final String TABLE_NAME = "contract";
        public static final String COLUMN_CON_NO = "con_no";
        public static final String COLUMN_CON_DATE = "con_date";
        public static final String COLUMN_CUST_NO = "cust_no";
        public static final String COLUMN_TOT_AMT = "tot_amt";
        public static final String COLUMN_PAY_AMT = "pay_amt";
        public static final String COLUMN_PERIOD = "period";
        public static final String COLUMN_BAL_AMT = "bal_amt";
        public static final String COLUMN_DISC_AMT = "disc_amt";
    }
}
