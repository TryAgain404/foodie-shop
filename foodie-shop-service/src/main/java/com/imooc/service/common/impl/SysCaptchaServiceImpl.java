package com.imooc.service.common.impl;

import com.google.code.kaptcha.Producer;
import com.imooc.framework.redis.RedisCache;
import com.imooc.service.common.SysCaptchaService;
import com.imooc.utils.Constants;
import com.imooc.utils.StringUtils;
import com.imooc.utils.exception.RRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

/**
 * 验证码
 * @author TryAgain404
 * @mail TryAgain500@163.com
 * @date 2020-12-31 16:29
 */
@Service("sysCaptchaService")
public class SysCaptchaServiceImpl implements SysCaptchaService {

    @Autowired
    private Producer producer;

    @Autowired
    private RedisCache redisCache;

    @Override
    public BufferedImage getCaptcha(String uuid) {
        if(StringUtils.isBlank(uuid)){
            throw new RRException("uuid不能为空");
        }
        //生成文字验证码
        String verifyCode = producer.createText();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        redisCache.setCacheObject(verifyKey, verifyCode, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);

        return producer.createImage(verifyCode);
    }
}
