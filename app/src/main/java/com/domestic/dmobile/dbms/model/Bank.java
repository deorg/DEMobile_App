package com.domestic.dmobile.dbms.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Bank {
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
        @SerializedName("CHANNEL_ID")       private String channelId;
        @SerializedName("CHANNEL_NAME")     private String channelName;
        @SerializedName("CHANNEL_IMG")      private String channelImg;

        //region ----- Get, Set ----------
        public String getChannelId() {
            return channelId;
        }

        public void setChannelId(String channelId) {
            this.channelId = channelId;
        }

        public String getChannelName() {
            return channelName;
        }

        public void setChannelName(String channelName) {
            this.channelName = channelName;
        }

        public String getChannelImg() {
            return channelImg;
        }

        public void setChannelImg(String channelImg) {
            this.channelImg = channelImg;
        }
        //endregion
    }
}
