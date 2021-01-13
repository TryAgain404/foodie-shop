package com.imooc.core.validate.code.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author TryAgain404
 * @mail TryAgain500@163.com
 * @date 2021-1-12 11:31
 */
@Component
public class SmsCodeSenderImpl implements SmsCodeSender {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void send(String mobile, String code) {
        logger.warn("请配置真实的短信验证码发送器(SmsCodeSender)");
        logger.info("向手机"+mobile+"发送短信验证码"+code);
    }
}
