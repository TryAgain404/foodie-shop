package com.imooc.utils;

import com.imooc.utils.enums.ResultEnum;
import lombok.Data;

/**
 * @author TryAgain404
 * @date 2020-10-22 17:26
 */
@Data
public class R<T> {
    /**
     * 错误码
     */
    private final Integer code;

    /**
     * 提示信息
     */
    private final String msg;

    /**
     * 具体的内容
     */
    private T data;

    /**
     * 成功时调用, 没有data内容
     * @return
     */
    public static <String> R<String> success() {
        return R.build(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), null);
    }

    /**
     * 成功时候的调用
     *
     * @param <T> 返回实体的类型
     * @return Result
     */
    public static <T> R<T> success(T data) {
        return new R<>(data);
    }

    /**
     * 根据返回的状态枚举, 构建返回结果
     *
     * @param resultEnum {@link ResultEnum} 返回状态枚举
     * @return Result
     */
    public static <Object> R<Object> build(ResultEnum resultEnum) {
        return new R<>(resultEnum);
    }

    /**
     * 根据自定义状态码{@code code}和自定义提示信息{@code msg}构建返回结果
     *
     * @param code 自定义状态码
     * @param msg 自定义提示信息
     * @return Result
     */
    public static <Object> R<Object> build(Integer code, String msg) {
        return new R<>(code, msg);
    }

    /**
     * 根据自定义状态码{@code code}, 自定义提示信息{@code msg}以及返回实体{@code T}构建返回结果
     *
     * @param code 自定义状态码
     * @param msg 自定义提示信息
     * @param <T> 返回实体的类型
     * @return Result
     */
    public static <T> R<T> build(Integer code, String msg, T data) {
        return new R<>(code, msg, data);
    }

    /**
     * 出错时调用, 自定义提示信息{@code msg}
     *
     * @param msg 自定义提示信息
     * @param <T> 返回实体的类型
     * @return Result
     */
    public static <T> R<T> error(String msg) {
        return new R<>(msg);
    }

    /**
     * 出错时调用, 根据返回实体{@code T}构建返回结果
     *
     * @param data 返回实体
     * @param <T> 返回实体的类型
     * @return Result
     */
    public static <T> R<T> error(T data) {
        return new R<>(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg(), data);
    }

    private R(T data) {
        this.code = ResultEnum.SUCCESS.getCode();
        this.msg = ResultEnum.SUCCESS.getMsg();
        this.data = data;
    }

    private R(String msg) {
        this.code = ResultEnum.ERROR.getCode();
        this.msg = msg;
        this.data = null;

    }

    private R(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }

    private R(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private R(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
