package com.imooc.core.validate.code.sms;

import lombok.Data;

/**
 * @author TryAgain404
 * @mail TryAgain500@163.com
 * @date 2021-1-12 11:29
 */
@Data
public class SmsCodeProperties {

    /**
     * 验证码长度
     */
    private int length = 6;
    /**
     * 过期时间
     */
    private int expireIn = 60;
}
