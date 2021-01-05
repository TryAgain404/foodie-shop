package com.imooc.core.validate.code.impl;

import com.imooc.core.validate.ValidateCode;
import com.imooc.core.validate.code.SysCaptchaService;
import com.imooc.core.validate.code.ValidateCodeProcessor;
import com.imooc.enums.ValidateCodeType;
import com.imooc.framework.redis.RedisCache;
import com.imooc.utils.Constants;
import com.imooc.utils.exception.RRException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * @author TryAgain404
 * @mail TryAgain500@163.com
 * @date 2021-1-4 11:24
 */
public abstract class AbstractValidateCodeProcessor<T extends ValidateCode> implements ValidateCodeProcessor {
    /**
     * 收集系统中所有的 {@link com.imooc.core.validate.code.SysCaptchaService} 接口的实现。
     */
    @Autowired
    private Map<String, SysCaptchaService> validateCodeGenerators;

    @Autowired
    RedisCache redisCache;

    @Override
    public void create(ServletWebRequest request) throws Exception {
        T validateCode = generate(request);
        send(request, validateCode);
    }

    protected abstract void send(ServletWebRequest request, T validateCode);

    @Override
    public void validate(ServletWebRequest servletWebRequest, String uuid) {
        ValidateCodeType codeType = getValidateCodeType();
        String verifyKey = "";
        if (codeType.getParamNameOnValidate().equals("SMS")) {
            verifyKey = Constants.CAPTCHA_CODE_SMS_KEY + uuid;
        } else {
            verifyKey = Constants.CAPTCHA_CODE_IMAGE_KEY + uuid;
        }

        T code = (T) redisCache.getCacheObject(verifyKey);

        String codeInRequest;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(),
                    codeType.getParamNameOnValidate());
        } catch (ServletRequestBindingException e) {
            throw new RRException("获取验证码的值失败");
        }

        if (StringUtils.isBlank(codeInRequest)) {
            throw new RRException(codeType + "验证码的值不能为空");
        }

        if (code == null) {
            throw new RRException(codeType + "验证码不存在");
        }

        if (code.isExpried()) {
            redisCache.deleteObject(verifyKey);
            throw new RRException(codeType + "验证码已过期");
        }

        if (!StringUtils.equals(code.getCode(), codeInRequest)) {
            throw new RRException(codeType + "验证码不匹配");
        }

        redisCache.deleteObject(verifyKey);

    }

    /**
     * 根据请求的url获取校验码的类型
     * @return
     */
    private ValidateCodeType getValidateCodeType() {
        String type = StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcessor");
        ValidateCodeType validateCodeType = ValidateCodeType.valueOf(type.toUpperCase());
        System.err.println(validateCodeType.getParamNameOnValidate());
        return ValidateCodeType.valueOf(type.toUpperCase());
    }

    /**
     * 生成校验码
     *
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    private T generate(ServletWebRequest request) {
        String type = getValidateCodeType().toString().toLowerCase();
        String generatorName = type + SysCaptchaService.class.getSimpleName();
        SysCaptchaService validateCodeGenerator = validateCodeGenerators.get(generatorName);
        if (validateCodeGenerator == null) {
            throw new RRException("验证码生成器" + generatorName + "不存在");
        }
        return (T) validateCodeGenerator.getCaptcha(request);
    }
}
