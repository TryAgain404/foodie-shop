//package com.imooc.core.validate.code.filter.authentication;
//
//import com.imooc.utils.Constants;
//import org.springframework.security.authentication.AuthenticationServiceException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.util.Assert;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * @author TryAgain404
// * @mail TryAgain500@163.com
// * @date 2021-1-5 11:09
// */
//public class SmsAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
//    public String mobileParameter = Constants.SPRING_SECURITY_FORM_MOBILE_KEY;
//    private boolean postOnly = true;
//
//    public SmsAuthenticationFilter() {
//        super(new AntPathRequestMatcher("/mobile/login", "POST"));
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        if (this.postOnly && !"POST".equals(request.getMethod())) {
//            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
//        } else {
//            String mobile = this.obtainMobile(request);
//            if (mobile == null) {
//                mobile = "";
//            }
//
//            mobile = mobile.trim();
//            SmsAuthenticationToken authRequest = new SmsAuthenticationToken(mobile);
//            this.setDetails(request, authRequest);
//            return this.getAuthenticationManager().authenticate(authRequest);
//        }
//    }
//
//
//    protected String obtainMobile(HttpServletRequest request) {
//        return request.getParameter(this.mobileParameter);
//    }
//
//    protected void setDetails(HttpServletRequest request, SmsAuthenticationToken authRequest) {
//        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
//    }
//
//    public void setMobileParameter(String mobileParameter) {
//        Assert.hasText(mobileParameter, "Username parameter must not be empty or null");
//        this.mobileParameter = mobileParameter;
//    }
//
//    public void setPostOnly(boolean postOnly) {
//        this.postOnly = postOnly;
//    }
//
//    public final String getMobileParameter() {
//        return this.mobileParameter;
//    }
//}