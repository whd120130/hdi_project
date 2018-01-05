package com.hdi.interceptor;

import com.hdi.controller.BaseController;
import com.hdi.model.commons.ResultStatus;
import com.hdi.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        String userId = (String) request.getSession().getAttribute("id");
        String requestURI = request.getRequestURI();
        String ipAddress = request.getRemoteAddr();
        String loginUrl = "/login.html";
        if (HandlerMethod.class.equals(handler.getClass())) {
            HandlerMethod method = (HandlerMethod) handler;
            Object controller = method.getBean();
            if (controller instanceof BaseController) {
                String uri = requestURI.substring(requestURI.lastIndexOf("/"));
                if (uri.startsWith("/login") || uri.startsWith("/imageLogin") || uri.startsWith("/checkLogin")){
                    ((BaseController) method.getBean()).setId(Integer.parseInt(userId));
                    return true;
                }else {
                    // 非法请求 重定向到登录页面
                    response.sendRedirect(request.getContextPath() + loginUrl);
                    return false;
                }
//                if (StringUtil.isEmpty(userId)) {
////                    logger.error(ResultStatus.LOGIN_ERROE.getMessage());
////                    ResultBean resultBean = ResultBean.buildError(ResultStatus.LOGIN_ERROE.getCode(),ResultStatus.LOGIN_ERROE.getMessage());
////                    response.setCharacterEncoding("UTF-8");
////                    response.setContentType("application/json;charset=UTF-8");
////                    response.getWriter().write(JSONObject.toJSONString(resultBean));
//
//                    return false;
//                } else {
//                    ((BaseController) method.getBean()).setId(Integer.parseInt(userId));
//                    logger.info(Thread.currentThread().getName() + " set userId:" + userId);
//                    return true;
//                }
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
