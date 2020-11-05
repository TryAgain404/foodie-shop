package com.imooc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.entitys.bo.UserBO;
import com.imooc.entitys.Users;
import com.imooc.mapper.UsersMapper;
import com.imooc.service.UsersService;
import com.imooc.utils.MD5Utils;
import com.imooc.utils.enums.Sex;
import com.imooc.utils.exception.RRException;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author TryAgain404
 */
@Service("usersService")
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    private static final String USER_FACE = "http://122.152.205.72:88/group1/M00/00/05/CpoxxFw_8_qAIlFXAAAcIhVPdSg994.png";
    @Autowired
    private Sid sid;

    @Override
    public boolean getUsernameIsExit(String username) {

        return getOne(new QueryWrapper<Users>().lambda().eq(Users::getUsername, username)) == null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Users saveUser(UserBO userBO) {
        //sha256加密
        Users user = new Users();
        user.setId(sid.nextShort());
        user.setUsername(userBO.getUsername());
        user.setPassword(MD5Utils.getMD5Str(userBO.getPassword()));
        user.setFace(USER_FACE);
        user.setSex(Sex.secret.type);
        user.setNickname(userBO.getUsername());
        user.setCreatedTime(new Date());
        user.setBirthday(new Date());
        user.setUpdatedTime(new Date());
        boolean isSave = this.save(user);
        if (isSave) {
            setNullProperty(user);
            System.err.println(user);
            return user;
        }
        throw new RRException("新建用户失败");
    }

    @Override
    public void deleteUser(String id) {
        this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Users users) {
        this.updateById(users);
    }

    @Override
    public Users login(UserBO user) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        queryWrapper.eq("password", MD5Utils.getMD5Str(user.getPassword()));
        Users userResult = this.getOne(queryWrapper);
        if (userResult == null) {
            throw new RRException("用户名或者密码错误");
        }
        setNullProperty(userResult);
        return userResult;
    }

    private void setNullProperty(Users userResult) {
        userResult.setPassword(null);
        userResult.setMobile(null);
        userResult.setEmail(null);
        userResult.setCreatedTime(null);
        userResult.setUpdatedTime(null);
        userResult.setBirthday(null);
    }
}
