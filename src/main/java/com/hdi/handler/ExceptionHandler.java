package com.hdi.handler;

import com.hdi.model.commons.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理
 * @auto wanghuidong
 * @date 2018/1/1.
 * @version 1.0
 */
@ControllerAdvice
public class ExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultBean handler(Exception e){
        logger.error(e.getMessage());
        if (e instanceof CommonException){
            CommonException exception = (CommonException)e;
            return ResultBean.buildError(exception.getCode(),exception.getMessage());
        }
        return ResultBean.buildError(e.getMessage());
    }
}
