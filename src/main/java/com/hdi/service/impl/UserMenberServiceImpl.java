package com.hdi.service.impl;
import com.alibaba.fastjson.JSONObject;
import com.hdi.dao.UserMenberRepository;
import com.hdi.handler.CommonException;
import com.hdi.model.UserMenbers;
import com.hdi.model.commons.ResultStatus;
import com.hdi.service.UserMenberService;
import com.hdi.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
public class UserMenberServiceImpl implements UserMenberService {


    //这里的单引号不能少，否则会报错，被识别是一个对象
    private static final String CACHE_KEY = "'user'";
    private static final String DEMO_CACHE_NAME = "users";


    private static final Logger logger = LoggerFactory.getLogger(UserMenberServiceImpl.class);
    @Autowired
    private UserMenberRepository userMenberRepository;

    /**
     * 检查对象
     * @param userMenbers
     * @throws Exception
     */
    @Override
    @Transactional
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
        userMenbers.setPassword(BCryptUtil.encrypt(userMenbers.getPassword().toLowerCase()));
        userMenbers.setSecondPwd(BCryptUtil.encrypt(userMenbers.getSecondPwd().toLowerCase()));
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
    //@Cacheable(value=DEMO_CACHE_NAME,key="'user_'+#uuid")
    @Override
    public PageTool<UserMenbers> findInviterByPage(PageTool<UserMenbers> pageTool, String inviter) throws Exception{
        PageRequest pageable = new PageRequest(pageTool.getPageNumber()-1,pageTool.getPageSize());
        Page<UserMenbers> datas = this.userMenberRepository.findByInviter(inviter,pageable);
        pageTool.setTotal((int) datas.getTotalElements());
        pageTool.setList(datas.getContent());
        return pageTool;
    }
    /**
     * 获取我的城邦
     * @param menberCode
     * @return
     * @throws Exception
     */
    @Override
    public UserMenbers myCityState(String menberCode) throws Exception {
        return getMyCityState(userMenberRepository.findByMenberCode(menberCode));
    }

    /**
     * 获取二级城邦
     * @param user
     * @return
     */
    private UserMenbers getMyCityState(UserMenbers user)throws Exception{
        if (user!=null){
            UserMenbers leftOne = userMenberRepository.findByMenberCode(user.getPutPeopleLeft());
            UserMenbers rigthOne = userMenberRepository.findByMenberCode(user.getPutPeopleRight());
            if (leftOne!=null && StringUtil.isNotEmpty(leftOne.getPutPeopleLeft())){
                user.setLeftUser(leftOne);
            }
            if (rigthOne!=null && StringUtil.isNotEmpty(leftOne.getPutPeopleRight())){
                user.setRigthUser(rigthOne);
            }
            if(leftOne!=null){
                UserMenbers leftSecont = userMenberRepository.findByMenberCode(leftOne.getPutPeopleLeft());
                UserMenbers rigthSecont = userMenberRepository.findByMenberCode(leftOne.getPutPeopleRight());
                if (leftSecont!=null){
                    leftOne.setLeftUser(leftSecont);
                }
                if (rigthSecont!=null){
                    leftOne.setRigthUser(rigthSecont);
                }
            }
            if(rigthOne!=null){
                UserMenbers leftSecont = userMenberRepository.findByMenberCode(rigthOne.getPutPeopleLeft());
                UserMenbers rigthSecont = userMenberRepository.findByMenberCode(rigthOne.getPutPeopleRight());
                if (leftSecont!=null){
                    rigthOne.setLeftUser(leftSecont);
                }
                if (rigthSecont!=null){
                    rigthOne.setRigthUser(rigthSecont);
                }
            }
        }
        logger.info("获取我的城邦：【"+JSONObject.toJSONString(user)+"】");
        return user;
    }
    /**
     * 登录
     * @param menberCode
     * @param password
     * @return
     * @throws Exception
     */
    public UserMenbers login(String menberCode,String password){
        UserMenbers user = userMenberRepository.findByMenberCode(menberCode);
        if (BCryptUtil.check(password,user.getPassword().toLowerCase())){
            return user;
        }else {
            return null;
        }
    }
}
