package com.hdi.model.commons;

/**
 * 返回状态基类
 * @author  wanghuidong
 * @date 2017/12/27.
 * @version 1.0
 */
public enum ResultStatus {
    SUCCESS(0, "成功"),
    FAILD(1, "失败"),
    EXCEPTION(2, "系统异常"),
    LOGIN_ERROE(3,"用户名或密码错误"),
    PARAM_ERROR(1000, "参数错误"),
    SYSTEM_ERROR(1001, "系统错误"),
    PUTPEOPLE_RIGHT_ERROE(1001, "右安置人已经存在，不能放在该人的下面"),
    PUTPEOPLE_LEFT_ERROE(1002, "左安置人已经存在，不能放在该人的下面"),
    PUTPEOPLE_EXIST_ERROR(1003,"安置人不存在，请重新输入"),
    INVITER_NOTEXIST_ERROR(1004,"分享人不能为空");
    private int code;
    private String message;

    ResultStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 状态信息
     */
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 状态代码字符串
     */
    public String getName() {
        return this.name();
    }

    /**
     * 向外部输出的状态代码字符串
     */
    public String getOutputName() {
        return this.name();
    }

    @Override
    public String toString() {
        return getName();
    }
}
