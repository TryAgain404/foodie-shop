package com.imooc.config.security.authentication;

import com.imooc.core.validate.code.filter.authentication.SmsAuthenticationToken;
import com.imooc.service.common.impl.UserDetailsByMobileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 短信登录验证逻辑
 *
 * 由于短信验证码的验证在过滤器里已完成，这里直接读取用户信息即可。
 * @author TryAgain404
 * @mail TryAgain500@163.com
 * @date 2021-1-5 14:44
 */
public class SmsAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsByMobileServiceImpl userDetailsByMobileService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        SmsAuthenticationToken authenticationToken = (SmsAuthenticationToken) authentication;

        UserDetails user = userDetailsByMobileService.loadUserByUsername((String) authenticationToken.getPrincipal());

        if (user == null) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }

        SmsAuthenticationToken authenticationResult = new SmsAuthenticationToken(user, user.getAuthorities());

        authenticationResult.setDetails(authenticationToken.getDetails());

        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsByMobileService;
    }

    public void setUserDetailsService(UserDetailsByMobileServiceImpl userDetailsByMobileService) {
        this.userDetailsByMobileService = userDetailsByMobileService;
    }
}
