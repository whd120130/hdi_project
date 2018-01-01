package com.hdi.service;

import com.hdi.model.UserMembers;
import com.hdi.utils.PageTool;

public interface UserMenberService{
    /**
     * 检查对象
     * @param userMembers
     * @throws Exception
     */
    public void chechUserMenber(UserMembers userMembers) throws Exception;
    /**
     * 保存会员信息
     * @param userMembers
     * @return
     * @throws Exception
     */
    public UserMembers save(UserMembers userMembers) throws Exception;
    /**
     * 查询当前会员号
     * @param memberCode
     * @return
     */
    public UserMembers findByMemberCode(String memberCode);
    /**
     * 查询对象分页
     * @param pageTool
     * @param inviter
     * @return
     * @throws Exception
     */
    public PageTool<UserMembers> findInviterByPage(PageTool<UserMembers> pageTool, String inviter) throws Exception;
}