package com.imooc.config.security;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.imooc.utils.R;
import com.imooc.utils.ServletUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author TryAgain404
 * @mail TryAgain500@163.com
 * @date 2021-1-5 15:24
 */
@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String msg = StringUtils.format("请求访问：" + request.getRequestURI() + "，认证失败");
        ServletUtils.renderString(response, JSON.toJSONString(R.error(msg)));
    }
}
