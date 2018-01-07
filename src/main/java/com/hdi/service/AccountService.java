package com.hdi.service;

/**
 * Created by 王慧东 on 2018/1/6.
 */
public interface AccountService {
    /**
     * 检查元素
     * @param country
     * @param bankName
     * @param realName
     * @param bankNo
     * @param bankAccount
     * @param idNo
     * @param mobile
     * @param address
     * @return
     */
    public boolean checkElement(String country,String bankName,String realName,String bankNo,String bankAccount,String idNo,String mobile,String address);
    /**
     * 修改登录密码
     * @param oldPassword
     * @param newPassword
     * @param repeatPassword
     * @return
     */
    public boolean updatePassword(String oldPassword,String newPassword,String repeatPassword);
}
