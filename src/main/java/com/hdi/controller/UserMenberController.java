package com.hdi.controller;

import com.alibaba.fastjson.JSONObject;
import com.hdi.model.UserMembers;
import com.hdi.model.commons.ResultBean;
import com.hdi.service.UserMenberService;
import com.hdi.utils.verificationCodeUtil.ImageRandUtils;
import com.hdi.utils.PageTool;
import com.hdi.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 会员信息controlller
 * @author 王慧东
 * @date 2017/12/30.
 * @version
 */
@RestController
@RequestMapping("/user")
public class UserMenberController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UserMenberController.class);
    @Autowired
    private UserMenberService userMenberService;

    /**
     * 用户登录（前端也要做校验）
     * @param userName
     * @param password
     * @param verificationCode
     * @return
     */
    @RequestMapping("/login")
    public ResultBean login(@RequestParam(name = "userName",required = true) String userName,
                            @RequestParam(name = "password",required = true)String password,
                            @RequestParam(name = "verificationCode",required = true)String verificationCode) throws Exception {
        if (StringUtil.isNotEmpty(userName)|| StringUtil.isNotEmpty(password) || StringUtil.isNotEmpty(verificationCode)){
            logger.info("登录信息必要参数不能空");
            throw new Exception("登录信息必要参数不能空");
        }
        return ResultBean.build();
    }

    /**
     * 增加会员信息
     * @param userMember (对象接收参数)
     * @return
     */
    @RequestMapping("/addUserMenber")
    public ResultBean addUserMenber(UserMembers userMember) throws Exception {
        logger.info("增加用户信息：" + JSONObject.toJSONString(userMember));
        if (StringUtil.isEmpty(userMember.getUserName()) || StringUtil.isEmpty(userMember.getPassword())|| StringUtil.isEmpty(userMember.getSecondPwd())||
                StringUtil.isEmpty(userMember.getRealName())||
                StringUtil.isEmpty(userMember.getAddress())||StringUtil.isEmpty(userMember.getIdNo())||
                StringUtil.isEmpty(userMember.getPutPeople())||StringUtil.isEmpty(userMember.getInviter())||
                StringUtil.isEmpty(userMember.getSite())){
            logger.info("请检查必要参数不能为空!");
            throw new Exception("请检查必要参数不能为空");
        }
        try {
            userMenberService.chechUserMenber(userMember);
        } catch (Exception e) {
            logger.error("保存用户信息发生错误：" + e.getMessage());
            throw new Exception("保存用户信息发生错误");
        }
        return ResultBean.build();
    }

    /**
     * 获取用户分享列表
     * @param inviter (分享人账号)
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @RequestMapping("/getInviters")
    public ResultBean<PageTool> getInviters(@RequestParam(value = "inviter",required = true) String inviter,
                                            @RequestParam(value = "pageNumber",required = false)Integer pageNumber,
                                            @RequestParam(value = "pageSize",required = false)Integer pageSize) throws Exception {
        ResultBean<PageTool> resultBean = ResultBean.build();
        if (StringUtil.isEmpty(inviter)){
            logger.info("分享人账号不能为空["+inviter+"]");
            throw new Exception("保存用户信息发生错误");
        }
        PageTool<UserMembers> pageTool = new PageTool<>();
        if (pageSize!=null || pageNumber!=null){
            pageTool.setPageNumber(pageNumber);
            pageTool.setPageSize(pageSize);
        }
        try {
            PageTool<UserMembers> page = userMenberService.findInviterByPage(pageTool,inviter);
            resultBean.setData(page);
            return resultBean;
        } catch (Exception e) {
            logger.error("获取用户分享列表:"+e.getMessage());
            throw new Exception("获取用户分享列表错误，请联系管理员！");
        }
    }
    /**
     * 图形验证码
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/genrandcode")
    public ResultBean genRandCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Object[] objs = ImageRandUtils.getCaptchaImage(80, 30, 25, 2, 5, false, true, ImageRandUtils.ComplexLevel.HARD);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ResultBean resultBean;
        try {
            try {
                ImageIO.write((BufferedImage) objs[0], "png", os);
                resultBean = ResultBean.build();
            } catch (IOException e) {
                resultBean = ResultBean.buildError(e.getMessage());
            }
            BASE64Encoder encoder = new BASE64Encoder();
            String base64Str = encoder.encode(os.toByteArray());
            Map map = new HashMap();
            map.put("base64", base64Str);
            map.put("image", objs[1]);
            resultBean.setData(map);
            return resultBean;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("图形验证码发生错误");
        }
    }
}
