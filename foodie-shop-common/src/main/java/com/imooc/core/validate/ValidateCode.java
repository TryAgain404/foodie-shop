package com.imooc.core.validate;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 验证码信息封装类
 * @author TryAgain404
 * @mail TryAgain500@163.com
 * @date 2021-1-4 11:13
 */
public class ValidateCode implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1588203828504660915L;

    private String code;

    private LocalDateTime expireTime;

    public ValidateCode(String code, int expireIn){
        this.code = code;
        this.expireTime = LocalDateTime.now().plusMinutes(expireIn);
    }

    public ValidateCode(String code, LocalDateTime expireTime){
        this.code = code;
        this.expireTime = expireTime;
    }

    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
}
