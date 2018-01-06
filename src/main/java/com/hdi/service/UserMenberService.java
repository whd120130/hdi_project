package com.hdi.service;

import com.hdi.model.UserMenbers;
import com.hdi.utils.PageTool;
public interface UserMenberService{

    /**
     * 检查对象
     * @param userMembers
     * @throws Exception
     */
    public void chechUserMenber(UserMenbers userMembers) throws Exception;

    /**
     * 保存会员信息
     * @param userMembers
     * @return
     * @throws Exception
     */
    public UserMenbers save(UserMenbers userMembers) throws Exception;

    /**
     * 查询当前会员号
     * @param memberCode
     * @return
     */
    public UserMenbers findByMemberCode(String memberCode);

    /**
     * 查询对象分页
     * @param pageTool
     * @param inviter
     * @return
     * @throws Exception
     */
    public PageTool<UserMenbers> findInviterByPage(PageTool<UserMenbers> pageTool, String inviter) throws Exception;

    /**
     * 登录
     * @param menberCode
     * @param password
     * @return
     * @throws Exception
     */
    public UserMenbers login(String menberCode,String password)throws Exception;

    /**
     * 获取我的城邦
     * @param menberCode
     * @return
     * @throws Exception
     */
    public UserMenbers myCityState(String menberCode)throws Exception;
}