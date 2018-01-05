package com.hdi.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.hdi.controller.BaseController;
import com.hdi.model.commons.ResultStatus;
import com.hdi.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 * @author wanghuidong
 * @date 2-17-12-31
 * @version 1.0
 */
public class ChanelInterceptor implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger(ChanelInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("****************preHandle");
        String id = request.getParameter("id");
        if (HandlerMethod.class.equals(handler.getClass())) {
            HandlerMethod method = (HandlerMethod) handler;
            Object controller = method.getBean();
            if (controller instanceof BaseController) {
                String ipAddress = request.getParameter("ipAddress");
                if (StringUtil.isNotEmpty(ipAddress)) {
                    ((BaseController) method.getBean()).setIpAddress(ipAddress);
                }
                if (StringUtil.isEmpty(id) || "0".equals(id)) {
                    logger.error("系统无法获取当前登录用户");
                    JSONObject jsonObj = new JSONObject();
                    jsonObj.put("status", ResultStatus.LOGIN_ERROE.getCode());
                    jsonObj.put("message", "系统无法获取当前登录用户");
                    jsonObj.put("value", null);
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().write(jsonObj.toString());
                    return false;
                } else {
                    ((BaseController) method.getBean()).setId(Integer.parseInt(id));
                    logger.info(Thread.currentThread().getName() + " set userId:" + id);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        logger.info("****************postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        logger.info("****************afterCompletion");
    }
}
