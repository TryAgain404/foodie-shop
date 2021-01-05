package com.imooc.core.validate.code;

import com.imooc.core.validate.code.image.ImageCodeGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author TryAgain404
 * @mail TryAgain500@163.com
 * @date 2021-1-4 15:53
 */
@Configuration
public class ValidateCodeBeanConfig {

    /**
     * 图片验证码图片生成器
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "imageSysCaptchaService")
    public SysCaptchaService imageSysCaptchaService() {
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        return codeGenerator;
    }

//    /**
//     * 短信验证码发送器
//     * @return
//     */
//    @Bean
//    @ConditionalOnMissingBean(SmsCodeSender.class)
//    public SmsCodeSender smsCodeSender() {
//        return new DefaultSmsCodeSender();
//    }
}
