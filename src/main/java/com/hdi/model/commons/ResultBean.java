package com.hdi.model.commons;

import com.hdi.utils.StringUtil;

import java.io.Serializable;

/**
 * 返回数据到前端基类
 * @author  王慧东
 * @date  2017/12/27.
 * @version
 */
public class ResultBean<T>extends AbstractResult implements Serializable {
    private static final long serialVersionUID = 867933019328199779L;

    protected ResultBean(int status, String message) {
        super(status, message);
    }

    public static <T> ResultBean<T> build() {
        return new ResultBean<T>(ResultStatus.SUCCESS.ordinal(), "success");
    }
    public static <T> ResultBean<T> buildError(String message) {
        if (StringUtil.isEmpty(message)){
            message = ResultStatus.PARAM_ERROR.getMessage();
        }
        return new ResultBean<T>(ResultStatus.PARAM_ERROR.ordinal(),message);
    }

    public static <T> ResultBean<T> buildError(int code,String message) {
        if (StringUtil.isEmpty(message)){
            message = ResultStatus.PARAM_ERROR.getMessage();
        }
        return new ResultBean<T>(code,message);
    }
    public static <T> ResultBean<T> build(String message) {
        return new ResultBean<T>(ResultStatus.SUCCESS.ordinal(), message);
    }

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 成功
     */
    public void success(T data) {
        this.success();
        this.data = data;
    }
}
