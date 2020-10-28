package com.imooc.utils.enums;

/**
 * @author TryAgain404
 * @date 2020-10-22 17:25
 */
public enum ResultEnum {

    /**
     * 成功
     */
    SUCCESS(200, "success"),
    /**
     * 失败
     */
    ERROR(500, "error"),
    /**
     * 传参出错
     */
    PARAM_ERROR(501, "params error"),
    /**
     * 必传参数丢失
     */
    PARAM_MISS_ERROR(502, "param miss"),
    /**
     * 记录不存在
     */
    RECORD_NOT_EXIST(503, "record is not exist!"),
    /**
     * 密码错误
     */
    PASSWORD_ERROR(504, "password error");

    private final Integer code;

    private final String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
