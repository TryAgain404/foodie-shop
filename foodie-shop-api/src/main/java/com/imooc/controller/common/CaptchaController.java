package com.imooc.controller.common;

import com.imooc.core.validate.ValidateCode;
import com.imooc.core.validate.code.SysCaptchaService;
import com.imooc.core.validate.code.ValidateCodeProcessorHolder;
import com.imooc.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import sun.security.util.SecurityConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码
 * @author TryAgain404
 * @mail TryAgain500@163.com
 * @date 2020-12-31 16:20
 */
@RestController
public class CaptchaController {

    @Autowired
    SysCaptchaService sysCaptchaService;
    @Autowired
    ValidateCodeProcessorHolder validateCodeProcessorHolder;

    /**
     * 验证码
     */
    @GetMapping(Constants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type)
            throws Exception {
        validateCodeProcessorHolder.findValidateCodeProcessor(type).create(new ServletWebRequest(request, response));
    }
}
