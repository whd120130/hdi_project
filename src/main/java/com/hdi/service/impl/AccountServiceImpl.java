package com.hdi.service.impl;

import com.hdi.handler.CommonException;
import com.hdi.model.commons.ResultStatus;
import com.hdi.service.AccountService;
import com.hdi.utils.StringUtil;
import org.springframework.stereotype.Component;

/**
 * Created by 王慧东 on 2018/1/6.
 */
@Component
public class AccountServiceImpl implements AccountService {
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
    @Override
    public boolean checkElement(String country, String bankName, String realName, String bankNo, String bankAccount, String idNo,String mobile,String address) {
        if (StringUtil.isEmpty(country) || StringUtil.isEmpty(bankName)||StringUtil.isEmpty(realName)||StringUtil.isEmpty(bankNo)
                ||StringUtil.isEmpty(bankAccount)||StringUtil.isEmpty(idNo)||StringUtil.isEmpty(address) || StringUtil.isEmpty(mobile)){
            if (idNo.length() != 18) {
                throw new CommonException(ResultStatus.IDNO_ERROR);
            }
            if (!bankNo.matches("^[0-9]*$")){
                throw new CommonException(ResultStatus.BANKNO_ERROR);
            }
            if (!mobile.matches("^[0-9]*$") && mobile.trim().length()>11){
                throw new CommonException(ResultStatus.MOBILE_ERROR);
            }
            throw new CommonException(ResultStatus.PARAM_ERROR);
        }
        return false;
    }
    /**
     * 修改登录密码
     * @param oldPassword
     * @param newPassword
     * @param repeatPassword
     * @return
     */
    @Override
    public boolean updatePassword(String oldPassword, String newPassword, String repeatPassword) {
        return false;
    }
}
