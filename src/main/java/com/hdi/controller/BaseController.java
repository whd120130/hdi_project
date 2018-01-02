package com.hdi.controller;

/**
 * 公用controller类
 * @author wanghuidong
 * @date 2018-1-2
 * @version 1.0
 */
public class BaseController {

    private ThreadLocal<Integer> Id = new ThreadLocal();
    /**
     * 用户客户端IP地址(用于防刷)
     */
    private ThreadLocal<String> ipAddress = new ThreadLocal();

    public void setId(Integer userId) {
        this.Id.set(userId);
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress.set(ipAddress);
    }

    public Integer getId() {
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
