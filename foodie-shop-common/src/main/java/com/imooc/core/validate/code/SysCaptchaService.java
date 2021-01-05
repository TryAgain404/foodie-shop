package com.imooc.core.validate.code;

import com.imooc.core.validate.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;


/**
 * @author TryAgain404
 * @date 2020-12-31 16:26
 */
public interface SysCaptchaService {

    /**
     * 获取图片验证码
     */
    ValidateCode getCaptcha(ServletWebRequest request);

}
