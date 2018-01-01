package com.hdi.model.commons;

/**
 * 返回状态基类
 * @author  王慧东
 * @date 2017/12/27.
 * @version 1.0
 */
public enum ResultStatus {
    SUCCESS(0, "成功"),
    FAILD(-1, "失败"),
    EXCEPTION(-1, "系统异常"),
    PARAM_ERROR(1000, "参数错误"),
    SYSTEM_ERROR(1001, "系统错误"),
    FILE_NOT_EXIST(1001, "文件不存在"),
    FILE_NOT_DOWNLOAD(1002, "文件没有下载"),
    FILE_NOT_GENERATE(1003, "文件没有生成"),
    FILE_NOT_STORAGE(1004, "文件没有入库"),
    SYSTEM_DB_ERROR(1005, "数据库系统错误"),
    FILE_ALREADY_DOWNLOAD(1003, "文件已经下载"),
    DATA_ALREADY_PEXISTS(1006, "数据已经存在"),
    INTEREST_FORMAT_ERROR(1007, "最低利率大于最高利率"),
    MONTHS_FORMAT_ERROR(1007, "最小月份大于最大月份"),
    TIME_FORMAT_ERROR(1007, "时间开始大于时间结束");

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
