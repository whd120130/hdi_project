package com.hdi.controller;

/**
 * Created by 王慧东 on 2017/12/31.
 */
public class BaseController {

    private ThreadLocal<Integer> Id = new ThreadLocal();
    /**
     * 用户客户端IP地址(用于防刷)
     */
    private ThreadLocal<String> ipAddress = new ThreadLocal();

    public void setUserId(Integer userId) {
        this.Id.set(userId);
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress.set(ipAddress);
    }

    public Integer getUserId() {
        if (Id != null) {
            return Id.get();
        }
        return null;
    }
    public String getIpAddress() {
        if (ipAddress != null) {
            return ipAddress.get();
        }
        return null;
    }
}
