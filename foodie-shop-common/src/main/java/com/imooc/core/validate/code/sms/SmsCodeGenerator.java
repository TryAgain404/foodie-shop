package com.imooc.core.validate.code.sms;

import com.imooc.core.validate.ValidateCode;
import com.imooc.core.validate.code.SysCaptchaService;
import com.imooc.framework.redis.RedisCache;
import com.imooc.utils.Constants;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author TryAgain404
 * @mail TryAgain500@163.com
 * @date 2021-1-12 11:26
 */
@Service("smsSysCaptchaService")
public class SmsCodeGenerator implements SysCaptchaService {

    @Autowired
    private RedisCache redisCache;


    @Override
    public ValidateCode getCaptcha(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(Constants.length);
        String mobile = request.getRequest().getParameter("mobile");
        redisCache.setCacheObject(Constants.CAPTCHA_CODE_SMS_KEY + mobile, new ValidateCode(code, Constants.CAPTCHA_EXPIRATION));
        return new ValidateCode(code, Constants.CAPTCHA_EXPIRATION);
    }
}
