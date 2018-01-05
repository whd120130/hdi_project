package com.hdi.controller;

import com.alibaba.fastjson.JSONObject;
import com.hdi.handler.CommonException;
import com.hdi.model.UserMenbers;
import com.hdi.model.commons.ResultBean;
import com.hdi.model.commons.ResultStatus;
import com.hdi.service.UserMenberService;
import com.hdi.utils.verificationCodeUtil.ImageRandUtils;
import com.hdi.utils.PageTool;
import com.hdi.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 会员信息controlller
 *
 * @author wanghuidong
 * @date 2017/12/30.
 */
@RestController
@RequestMapping("/user")
public class UserMenberController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UserMenberController.class);
    @Autowired
    private UserMenberService userMenberService;

    /**
     * 用户登录（前端也要做校验）
     *
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping("/login")
    public ResultBean login(@RequestParam(name = "userName", required = true) String userName,
                            @RequestParam(name = "password", required = true) String password) throws Exception {
        if (StringUtil.isNotEmpty(userName) || StringUtil.isNotEmpty(password)) {
            throw new CommonException(ResultStatus.PARAM_ERROR);
        }
        return ResultBean.build();
    }

    /**
     * 增加会员信息
     *
     * @param userMember
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping("/addUserMenber")
    public ResultBean addUserMenber(@Valid UserMenbers userMember, BindingResult bindingResult) throws Exception {
        logger.info("增加用户信息：" + JSONObject.toJSONString(userMember));
        if (bindingResult.hasErrors()) {
            throw new Exception(bindingResult.getFieldError().getDefaultMessage());
        }
        userMenberService.chechUserMenber(userMember);
        return ResultBean.build();
    }

    /**
     * 获取用户分享列表
     *
     * @param inviter    (分享人账号)
     * @param pageNumber  第几页
     * @param pageSize 显示多少条
     * @return
     */
    @RequestMapping("/getInviters")
    public ResultBean<PageTool> getInviters(@RequestParam(value = "inviter", required = true) String inviter,
                                            @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                            @RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception {
        ResultBean<PageTool> resultBean = ResultBean.build();
        if (StringUtil.isEmpty(inviter)) {
            logger.info(ResultStatus.INVITER_NOTEXIST_ERROR.getMessage());
            throw new CommonException(ResultStatus.INVITER_NOTEXIST_ERROR);
        }
        PageTool<UserMenbers> pageTool = new PageTool<>();
        if (pageSize != null || pageNumber != null) {
            pageTool.setPageNumber(pageNumber);
            pageTool.setPageSize(pageSize);
        }
        PageTool<UserMenbers> page = userMenberService.findInviterByPage(pageTool, inviter);
        resultBean.setData(page);
        return resultBean;
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

    /**
     * 我的城邦
     *
     * @param menberCode
     */
    @RequestMapping(value = "/myCityState")
    public ResultBean myCityState(@RequestParam(value = "menberCode") String menberCode) throws Exception {
        ResultBean resultBean = ResultBean.build();
        UserMenbers user = userMenberService.myCityState(menberCode);
        resultBean.setData(user);
        return resultBean;
    }
}
