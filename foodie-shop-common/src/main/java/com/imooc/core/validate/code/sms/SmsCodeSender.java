package com.imooc.core.validate.code.sms;

/**
 * @author TryAgain404
 * @date 2021-1-12 11:31
 */
public interface SmsCodeSender {

    /**
     * @param mobile
     * @param code
     */
    void send(String mobile, String code);
}
