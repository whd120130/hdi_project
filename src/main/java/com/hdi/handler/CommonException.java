package com.hdi.handler;


import com.hdi.model.commons.ResultStatus;

/**
 * Created by wanghuidong on 2018/1/2.
 */
public class CommonException extends RuntimeException {

    private int code;
    public CommonException(ResultStatus resultStatus) {
        super(resultStatus.getMessage());
        this.code = resultStatus.getCode();
    }
    public int getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }

}
