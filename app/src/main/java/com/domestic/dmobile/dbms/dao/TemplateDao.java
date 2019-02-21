package com.domestic.dmobile.dbms.dao;

import com.google.gson.annotations.SerializedName;

public class TemplateDao {
    @SerializedName("TEMP_NO")          private int tempNo;
    @SerializedName("TEMP_NAME")        private String tempName;

    public int getTempNo() {
        return tempNo;
    }

    public void setTempNo(int tempNo) {
        this.tempNo = tempNo;
    }

    public String getTempName() {
        return tempName;
    }

    public void setTempName(String tempName) {
        this.tempName = tempName;
    }
}
