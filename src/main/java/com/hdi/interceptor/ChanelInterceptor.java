package com.hdi.interceptor;

import com.hdi.controller.BaseController;
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
 * @author 王慧东
 * @date 2-17-12-31
 * @version 1.0
 */
@Component
public class ChanelInterceptor implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger(ChanelInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("****************preHandle");
        if (HandlerMethod.class.equals(handler.getClass())) {
            HandlerMethod method = (HandlerMethod) handler;
            Object controller = method.getBean();
            if (controller instanceof BaseController) {
                String id = request.getParameter("id");
                String ipAddress = request.getParameter("ipAddress");
                if (StringUtil.isNotEmpty(ipAddress)) {
                    ((BaseController) method.getBean()).setIpAddress(ipAddress);
                }
                if (StringUtil.isEmpty(id) || "0".equals(id)) {
                    logger.error("Id not found");
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
