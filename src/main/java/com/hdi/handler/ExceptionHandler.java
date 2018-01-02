package com.hdi.handler;

import com.hdi.model.commons.ResultBean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 王慧东 on 2018/1/1.
 */
@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultBean handler(Exception e){
        if (e instanceof CommonException){
            CommonException exception = (CommonException)e;
            return ResultBean.buildError(exception.getCode(),exception.getMessage());
        }
        return ResultBean.buildError(e.getMessage());
    }
}
