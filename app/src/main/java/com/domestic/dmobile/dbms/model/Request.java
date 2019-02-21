package com.domestic.dmobile.dbms.model;

public class Request {
    private String CustomerId;
    private String ContractNo;
    private String DeviceId;
    private int Amount;
    private String PhoneNumber;
    private String Description;
    private String ChannelCode;

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getContractNo() {
        return ContractNo;
    }

    public void setContractNo(String contractNo) {
        ContractNo = contractNo;
    }

    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String deviceId) {
        DeviceId = deviceId;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getChannelCode() {
        return ChannelCode;
    }

    public void setChannelCode(String channelCode) {
        ChannelCode = channelCode;
    }
}
