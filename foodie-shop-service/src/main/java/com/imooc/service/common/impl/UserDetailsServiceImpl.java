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
import org.springframework.stereotype.Service;

/**
 * @author TryAgain404
 * @mail TryAgain500@163.com
 * @date 2020-12-28 16:03
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    @Autowired
    UsersService usersService;
    @Autowired
    SysPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersService.getUserByUsername(username);
        if (StringUtils.isNull(user)) {
            logger.info("登录用户：{} 不存在.", username);
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        } else if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            logger.info("登录用户：{} 已被删除.", username);
            throw new RRException("对不起，您的账号：" + username + " 已被删除");
        } else if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            logger.info("登录用户：{} 已被停用.", username);
            throw new RRException("对不起，您的账号：" + username + " 已停用");
        }

        return createLoginUser(user);
    }

    private UserDetails createLoginUser(Users user) {
        return new LoginUser(user, permissionService.getMenuPermission(user));
    }
}
