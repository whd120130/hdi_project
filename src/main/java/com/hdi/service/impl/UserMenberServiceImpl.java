package com.hdi.service.impl;
import com.hdi.dao.UserMenberRepository;
import com.hdi.handler.CommonException;
import com.hdi.model.UserMenbers;
import com.hdi.model.commons.ResultStatus;
import com.hdi.service.UserMenberService;
import com.hdi.utils.CommonEnum;
import com.hdi.utils.CommonUtils;
import com.hdi.utils.PageTool;
import com.hdi.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;

/**
 * 会员信息service
 * @author wanghuidong
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
     * @param userMenbers
     * @throws Exception
     */
    @Override
    public void chechUserMenber(UserMenbers userMenbers) throws Exception {
        String menberCode="";
        do {
            menberCode = CommonUtils.getInviteCode(5);
        }while (findByMemberCode(menberCode)!=null);
        userMenbers.setMenberCode(menberCode);
        UserMenbers putPeopleMenber = findByMemberCode(userMenbers.getPutPeople());
        if (null == putPeopleMenber){
            throw new CommonException(ResultStatus.PUTPEOPLE_EXIST_ERROR);
        }
        if (userMenbers.getSite()== CommonEnum.LEFY_PUTPEOPLE.ordinal()){
            if (StringUtil.isNotEmpty(putPeopleMenber.getPutPeopleLeft())){
                throw new CommonException(ResultStatus.PUTPEOPLE_LEFT_ERROE);
            }
            putPeopleMenber.setPutPeopleLeft(userMenbers.getMenberCode());
        }else if (userMenbers.getSite()==CommonEnum.RIGTH_PUTPEOPLE.ordinal()){
            if (StringUtil.isNotEmpty(putPeopleMenber.getPutPeopleRight())){
                throw new CommonException(ResultStatus.PUTPEOPLE_RIGHT_ERROE);
            }
            putPeopleMenber.setPutPeopleRight(userMenbers.getMenberCode());
        }
        save(userMenbers);
        save(putPeopleMenber);
        logger.info("保存会员信息成功");
    }

    /**
     * 保存会员信息
     * @param userMenbers
     * @return
     * @throws Exception
     */
    @Override
    public UserMenbers save(UserMenbers userMenbers) throws Exception {
        return userMenberRepository.save(userMenbers);
    }
    /**
     * 查询当前会员号
     * @param memberCode
     * @return
     */
    @Override
    public UserMenbers findByMemberCode(String memberCode) {
        return userMenberRepository.findByMenberCode(memberCode);
    }

    /**
     * 查询对象分页
     * @param pageTool
     * @return
     * @throws Exception
     */
    @Override
    public PageTool<UserMenbers> findInviterByPage(PageTool<UserMenbers> pageTool, String inviter) throws Exception{
        PageRequest pageable = new PageRequest(pageTool.getPageNumber()-1,pageTool.getPageSize());
        Page<UserMenbers> datas = this.userMenberRepository.findByInviter(inviter,pageable);
        pageTool.setTotal((int) datas.getTotalElements());
        pageTool.setList(datas.getContent());
        return pageTool;
    }
    /**
     * 登录
     * @param userName
     * @param password
     * @return
     * @throws Exception
     */
    @Override
    public UserMenbers login(String userName, String password) throws Exception {
        return userMenberRepository.findByMenberCodeAndPassword(userName,password);
    }
}
