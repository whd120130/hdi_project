package com.hdi.controller;

import com.hdi.controller.base.BaseController;
import com.hdi.handler.CommonException;
import com.hdi.model.UserMenbers;
import com.hdi.model.commons.ResultBean;
import com.hdi.model.commons.ResultStatus;
import com.hdi.service.AccountService;
import com.hdi.service.UserMenberService;
import com.hdi.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 我的账户
 * @author wanghuidong
 * @date 2018/1/6.
 * @version 1.0
 */
@RestController
@RequestMapping("account")
public class AccountController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserMenberService userMenberService;

    /**
     * 编辑个人资料
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
    @RequestMapping("/updateAccount")
    public ResultBean updateAccount(@RequestParam(value ="country",required = true) String country,
                                    @RequestParam(value = "bankName",required = true)String bankName,
                                    @RequestParam(value = "realName",required = true)String realName,
                                    @RequestParam(value = "bankNo",required = true)String bankNo,
                                    @RequestParam(value = "bankAccount",required =true )String bankAccount,
                                    @RequestParam(value = "idNo",required = true)String idNo,
                                    @RequestParam(value = "mobile",required = true)String mobile,
                                    @RequestParam(value = "address",required = true)String address){
        if (StringUtil.isNotEmpty(getMenberCode())){
            throw new CommonException(ResultStatus.NOT_LOGIN_IN);
        }
        accountService.checkElement(country,bankName,realName,bankNo,bankAccount,idNo,mobile,address);
        UserMenbers user = userMenberService.findByMemberCode(getMenberCode());
        user.setCountry(country);
        user.setBankName(bankName);
        user.setRealName(realName);
        user.setBankNo(bankNo);
        user.setBankAccount(bankAccount);
        user.setIdNo(idNo);
        user.setMobile(mobile);
        user.setAddress(address);
        try {
            userMenberService.save(user);
        } catch (Exception e) {
            throw new CommonException(ResultStatus.EXCEPTION);
        }
        return ResultBean.build();
    }
    @RequestMapping("/updatePassword")
    public ResultBean updatePassword(@RequestParam(value = "oldPassword",required = true)String oldPassword,
                                     @RequestParam(value = "newPassword",required = true)String newPassword,
                                     @RequestParam(value = "repeatPassword",required = true)String repeatPassword){
        accountService.updatePassword(oldPassword,newPassword,repeatPassword);
        return null;
    }
}
