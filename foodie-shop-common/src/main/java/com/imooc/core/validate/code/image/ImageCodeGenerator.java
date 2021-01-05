package com.imooc.core.validate.code.image;

import com.google.code.kaptcha.Producer;
import com.imooc.core.validate.ValidateCode;
import com.imooc.core.validate.code.SysCaptchaService;
import com.imooc.framework.redis.RedisCache;
import com.imooc.utils.Constants;
import com.imooc.utils.StringUtils;
import com.imooc.utils.exception.RRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.concurrent.TimeUnit;

/**
 * @author TryAgain404
 * @mail TryAgain500@163.com
 * @date 2021-1-4 15:06
 */
public class ImageCodeGenerator implements SysCaptchaService {

    @Autowired
    private Producer producer;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ValidateCode getCaptcha(ServletWebRequest request) {
        String uuid = request.getRequest().getParameter("uuid");
        if(StringUtils.isBlank(uuid)){
            throw new RRException("uuid不能为空");
        }
//        //生成文字验证码
        String verifyCode = producer.createText();
        String verifyKey = Constants.CAPTCHA_CODE_IMAGE_KEY + uuid;
        ValidateCode validateCode = new ValidateCode(verifyCode, Constants.CAPTCHA_EXPIRATION);

        redisCache.setCacheObject(verifyKey, validateCode);
        return new ImageCode(producer.createImage(verifyCode), verifyCode, Constants.CAPTCHA_EXPIRATION);
    }
}
