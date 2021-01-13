package com.imooc.core.validate.code.sms;

import com.imooc.core.validate.ValidateCode;
import com.imooc.core.validate.code.impl.AbstractValidateCodeProcessor;
import com.imooc.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author TryAgain404
 * @mail TryAgain500@163.com
 * @date 2021-1-12 11:36
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {
    @Autowired
    SmsCodeSender smsCodeSender;
    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws ServletRequestBindingException {
        String paramName = Constants.SPRING_SECURITY_FORM_MOBILE_KEY;
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
        smsCodeSender.send(mobile, validateCode.getCode());
    }
}
