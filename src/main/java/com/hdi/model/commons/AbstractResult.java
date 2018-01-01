package com.hdi.model.commons;

/**
 * Created by 王慧东 on 2017/12/27.
 */
public abstract class AbstractResult {
    private int status;
    private String message;

    protected AbstractResult(int status, String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * 提供静态方法检测是否成功，避免null值无法检测自身的情况
     *
     * @param result
     * @return 是否成功状态
     */
    public static boolean isSuccess(AbstractResult result) {
        return result != null && result.status == ResultStatus.SUCCESS.ordinal();
    }

    /**
     * 同withStatus()方法，代替原方法
     *
     * @param status ResultStatus状态值
     * @return 当前实例
     */
    public AbstractResult withError(int status) {
        this.status = status;
        return this;
    }

    public AbstractResult withError(String message) {
        this.status = ResultStatus.SYSTEM_ERROR.ordinal();
        this.message = message;
        return this;
    }

    public AbstractResult withError(int status, String message) {
        this.message = message;
        this.status = status;
        return this;
    }
    public AbstractResult withError(ResultStatus status) {
        this.message = status.getMessage();
        this.status = status.getCode();
        return this;
    }


    /**
     * 设置状态为ResultStatus.SUCCESS
     *
     * @return 当前实例
     */
    public AbstractResult success() {
        this.status = ResultStatus.SUCCESS.ordinal();
        return this;
    }

    //public abstract Object getData();

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
