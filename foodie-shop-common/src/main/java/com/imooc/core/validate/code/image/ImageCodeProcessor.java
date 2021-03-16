package com.imooc.core.validate.code.image;

import com.imooc.core.validate.code.impl.AbstractValidateCodeProcessor;
import com.imooc.framework.redis.RedisCache;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * @author TryAgain404
 * @mail TryAgain500@163.com
 * @date 2021-1-4 14:44
 */
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {
    @Autowired
    RedisCache redisCache;

    @SneakyThrows
    @Override
    protected void send(ServletWebRequest request, ImageCode validateCode) {
        assert request.getResponse() != null;
        ImageIO.write(validateCode.getImage(), "JPEG", request.getResponse().getOutputStream());
    }
}