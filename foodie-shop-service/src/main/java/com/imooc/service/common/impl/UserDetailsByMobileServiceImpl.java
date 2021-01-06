package com.imooc.service.common.impl;

import com.imooc.entitys.Users;
import com.imooc.entitys.security.LoginUser;
import com.imooc.enums.UserStatus;
import com.imooc.service.UsersService;
import com.imooc.utils.StringUtils;
import com.imooc.utils.exception.RRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author TryAgain404
 * @mail TryAgain500@163.com
 * @date 2021-1-5 15:09
 */
@Component("userDetailsByMobileService")
public class UserDetailsByMobileServiceImpl implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    @Autowired
    UsersService usersService;
    @Autowired
    SysPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
        Users user = usersService.getUserByMobile(mobile);
        if (StringUtils.isNull(user)) {
            logger.info("登录用户：{} 不存在.", mobile);
            throw new UsernameNotFoundException("手机：" + mobile + " 不存在");
        } else if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            logger.info("手机：{} 已被删除.", mobile);
            throw new RRException("对不起，您的账号：" + mobile + " 已被删除");
        } else if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            logger.info("手机：{} 已被停用.", mobile);
            throw new RRException("对不起，您的账号：" + mobile + " 已停用");
        }

        return createLoginUser(user);
    }

    private UserDetails createLoginUser(Users user) {
        return new LoginUser(user, permissionService.getMenuPermission(user));
    }
}
