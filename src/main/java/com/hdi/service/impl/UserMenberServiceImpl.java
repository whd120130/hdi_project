package com.hdi.service.impl;
import com.hdi.dao.UserMenberRepository;
import com.hdi.model.UserMembers;
import com.hdi.service.UserMenberService;
import com.hdi.utils.CommonEnum;
import com.hdi.utils.CommonUtils;
import com.hdi.utils.PageTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;

/**
 * 会员信息service
 * @author 王慧东
 * @date 2017/12/29.
 * @version
 */
@Component
@Transactional
public class UserMenberServiceImpl implements UserMenberService {
    private static final Logger logger = LoggerFactory.getLogger(UserMenberServiceImpl.class);
    @Autowired
    private UserMenberRepository userMenberRepository;

    /**
     * 检查对象
     * @param userMembers
     * @throws Exception
     */
    @Override
    public void chechUserMenber(UserMembers userMembers) throws Exception {
        String menberCode="";
        do {
            menberCode = CommonUtils.getInviteCode(5);
        }while (findByMemberCode(menberCode)!=null);
        userMembers.setMemberCode(menberCode);
        UserMembers putPeopleMenber = findByMemberCode(userMembers.getPutPeople());
        if (null == putPeopleMenber){
            throw new Exception("安置人：["+ userMembers.getPutPeople()+"],不存在请重新输入！");
        }
        if (userMembers.getSite()== CommonEnum.LEFY_PUTPEOPLE.ordinal()){
            putPeopleMenber.setPutPeopleLeft(userMembers.getMemberCode());
        }else if (userMembers.getSite()==CommonEnum.RIGTH_PUTPEOPLE.ordinal()){
            putPeopleMenber.setPutPeopleRight(userMembers.getMemberCode());
        }
        save(userMembers);
        save(putPeopleMenber);
        logger.info("保存会员信息成功");
    }

    /**
     * 保存会员信息
     * @param userMembers
     * @return
     * @throws Exception
     */
    @Override
    public UserMembers save(UserMembers userMembers) throws Exception {
        return userMenberRepository.save(userMembers);
    }
    /**
     * 查询当前会员号
     * @param memberCode
     * @return
     */
    @Override
    public UserMembers findByMemberCode(String memberCode) {
        return userMenberRepository.findByMemberCode(memberCode);
    }

    /**
     * 查询对象分页
     * @param pageTool
     * @return
     * @throws Exception
     */
    @Override
    public PageTool<UserMembers> findInviterByPage(PageTool<UserMembers> pageTool,String inviter) throws Exception{
        PageRequest pageable = new PageRequest(pageTool.getPageNumber()-1,pageTool.getPageSize());
        Page<UserMembers> datas = this.userMenberRepository.findByInviter(inviter,pageable);
        pageTool.setTotal((int) datas.getTotalElements());
        pageTool.setList(datas.getContent());
        return pageTool;
    }
}
