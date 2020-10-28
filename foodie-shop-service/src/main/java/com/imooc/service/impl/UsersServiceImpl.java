package com.imooc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.entitys.BO.UsnerBO;
import com.imooc.entitys.Users;
import com.imooc.mapper.UsersMapper;
import com.imooc.service.UsersService;
import com.imooc.utils.enums.Sex;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service("usersService")
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    private static final String USER_FACE = "http://122.152.205.72:88/group1/M00/00/05/CpoxxFw_8_qAIlFXAAAcIhVPdSg994.png";
    @Autowired
    private Sid sid;

    @Override
    public Users getUsernameIsExit(String username) {
        Users user = baseMapper.getUsernameIsExit(username);
        return user;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveUser(UsnerBO usnerBO) {
        //sha256加密
        Users user = new Users();
        user.setId(sid.nextShort());
        user.setUsername(usnerBO.getUsername());
        user.setPassword(usnerBO.getPassword());
        user.setFace(USER_FACE);
        user.setSex(Sex.secret.type);
        user.setCreatedTime(new Date());
        user.setBirthday(new Date());
        user.setUpdatedTime(new Date());
        this.save(user);
    }

    @Override
    public void deleteUser(String id) {
        this.removeById(id);
    }

    @Override
    @Transactional
    public void update(Users users) {
        this.updateById(users);
    }
}
