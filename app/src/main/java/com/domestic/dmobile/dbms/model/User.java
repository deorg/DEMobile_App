package com.domestic.dmobile.dbms.model;

import com.google.gson.annotations.SerializedName;

public class User {
    public static int custNo;
    public static String custName;
    public static String citizenNo;
    public static String phoneNo;
    public static String simPhoneNo;
    public static String deviceId;
    public static String simSerial;
    public static String operator;
    public static String brand;
    public static String model;
    public static int apiVersion;
    public static String pinCode;
    public static String permit;
    public static String deviceStatus;

    //region ----- Get, Set ----------

    public static int getCustNo() {
        return custNo;
    }

    public static void setCustNo(int custNo) {
        User.custNo = custNo;
    }

    public static String getCustName() {
        return custName;
    }

    public static void setCustName(String custName) {
        User.custName = custName;
    }

    public static String getCitizenNo() {
        return citizenNo;
    }

    public static void setCitizenNo(String citizenNo) {
        User.citizenNo = citizenNo;
    }

    public static String getPhoneNo() {
        return phoneNo;
    }

    public static void setPhoneNo(String phoneNo) {
        if (phoneNo != null) phoneNo = phoneNo.replace("+66", "0");
        User.phoneNo = phoneNo;
    }

    public static String getSimPhoneNo() {
        return simPhoneNo;
    }

    public static void setSimPhoneNo(String simPhoneNo) {
        User.simPhoneNo = simPhoneNo;
    }

    public static String getDeviceId() {
        return deviceId;
    }

    public static void setDeviceId(String deviceId) {
        User.deviceId = deviceId;
    }

    public static String getSimSerial() {
        return simSerial;
    }

    public static void setSimSerial(String simSerial) {
        User.simSerial = simSerial;
    }

    public static String getOperator() {
        return operator;
    }

    public static void setOperator(String operator) {
        User.operator = operator;
    }

    public static String getBrand() {
        return brand;
    }

    public static void setBrand(String brand) {
        User.brand = brand;
    }

    public static String getModel() {
        return model;
    }

    public static void setModel(String model) {
        User.model = model;
    }

    public static int getApiVersion() {
        return apiVersion;
    }

    public static void setApiVersion(int apiVersion) {
        User.apiVersion = apiVersion;
    }

    public static String getPinCode() {
        return pinCode;
    }

    public static void setPinCode(String pinCode) {
        User.pinCode = pinCode;
    }

    public static String getPermit() {
        return permit;
    }

    public static void setPermit(String permit) {
        User.permit = permit;
    }

    public static String getDeviceStatus() {
        return deviceStatus;
    }

    public static void setDeviceStatus(String deviceStatus) {
        User.deviceStatus = deviceStatus;
    }

    //endregion

    public class Model {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_CUST_NO = "cust_no";
        public static final String COLUMN_CUST_NAME = "cust_name";
        public static final String COLUMN_CITIZEN_NO = "citizen_no";
        public static final String COLUMN_PHONE_NO = "phone_no";
        public static final String COLUMN_DEVICEID = "device_id";
        public static final String COLUMN_PINCODE = "pincode";
    }

    public class Register {
        @SerializedName("citizen_no")       private String citizenNo;
        @SerializedName("phone_no")         private String phoneNo;
        @SerializedName("phone_no_sim")     private String simPhoneNo;
        @SerializedName("device_id")        private String deviceId;
        @SerializedName("serial_sim")       private String simSerial;
        @SerializedName("operator_name")    private String operator;
        @SerializedName("brand")            private String brand;
        @SerializedName("model")            private String model;
        @SerializedName("api_version")      private int apiVersion;
        @SerializedName("pin")              private String pinCode;

        //region ----- Get, Set ----------

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

        public String getSimPhoneNo() {
            return simPhoneNo;
        }

        public void setSimPhoneNo(String simPhoneNo) {
            this.simPhoneNo = simPhoneNo;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getSimSerial() {
            return simSerial;
        }

        public void setSimSerial(String simSerial) {
            this.simSerial = simSerial;
        }

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public int getApiVersion() {
            return apiVersion;
        }

        public void setApiVersion(int apiVersion) {
            this.apiVersion = apiVersion;
        }

        public String getPinCode() {
            return pinCode;
        }

        public void setPinCode(String pinCode) {
            this.pinCode = pinCode;
        }

        //endregion


        public class Response {
            @SerializedName("code")             private int code;
            @SerializedName("message")          private String message;
            @SerializedName("data")             private Data data;

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
                @SerializedName("CUST_NO")      private int custNo;
                @SerializedName("CUST_NAME")    private String custName;
                @SerializedName("CITIZEN_NO")   private String citizenNo;
                @SerializedName("TEL")          private String phoneNo;
                @SerializedName("PERMIT")       private String permit;

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

                //endregion
            }
        }
    }






}
