package com.imooc.service.common;

import java.awt.image.BufferedImage;

/**
 * @author TryAgain404
 * @date 2020-12-31 16:26
 */
public interface SysCaptchaService {

    /**
     * 获取图片验证码
     */
    BufferedImage getCaptcha(String uuid);

}
