package com.hdi.HttpAop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * AOP日志打印
 * @author wanghuidong
 * @date 2018/1/1.
 * @version 1.0
 */
@Aspect
@Component
public class HttpRequestAspect {
    private static final Logger logger = LoggerFactory.getLogger(HttpRequestAspect.class);

    @Pointcut("execution(public * com.hdi.controller.*.*(..))")
    public void log(){
    }
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes =(ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("url={}",request.getRequestURL());
        logger.info("ip={}",request.getRemoteAddr());
        logger.info("id={}",request.getParameter("id"));
        logger.info("args={}",joinPoint.getArgs());
    }
    @After("log()")
    public void doAfter(){
        System.out.println("doAfter#################################");
    }
}
