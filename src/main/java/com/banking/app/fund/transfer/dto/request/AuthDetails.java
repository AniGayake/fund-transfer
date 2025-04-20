package com.banking.app.fund.transfer.dto.request;

public class AuthDetails {
    private String method;
    private int otp;
    private String deviceId;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        return "AuthDetails{" +
                "method='" + method + '\'' +
                ", otp=" + otp +
                ", deviceId='" + deviceId + '\'' +
                '}';
    }
}
