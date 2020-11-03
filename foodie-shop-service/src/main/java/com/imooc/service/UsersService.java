package com.imooc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.entitys.bo.UserBO;
import com.imooc.entitys.Users;

/**
 * 用户表
 *
 * @author TryAgain404
 * @email TryAgain500@163.com
 * @date 2020-10-21 11:33:24
 */
public interface UsersService extends IService<Users> {

    boolean getUsernameIsExit(String username);

    Users saveUser(UserBO users);

    void deleteUser(String id);

    void update(Users users);

    Users login(UserBO user);
}

