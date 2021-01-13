package com.imooc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.core.validate.ValidateCode;
import com.imooc.core.validate.code.filter.authentication.SmsAuthenticationToken;
import com.imooc.entitys.Users;
import com.imooc.entitys.bo.UserBO;
import com.imooc.entitys.security.LoginBody;
import com.imooc.entitys.security.LoginUser;
import com.imooc.framework.redis.RedisCache;
import com.imooc.mapper.UsersMapper;
import com.imooc.service.UsersService;
import com.imooc.service.common.impl.TokenService;
import com.imooc.utils.Constants;
import com.imooc.utils.MD5Utils;
import com.imooc.utils.enums.Sex;
import com.imooc.utils.exception.RRException;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author TryAgain404
 */
@Service("usersService")
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    private static final String USER_FACE = "http://122.152.205.72:88/group1/M00/00/05/CpoxxFw_8_qAIlFXAAAcIhVPdSg994.png";
    @Autowired
    private Sid sid;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;
    @Autowired
    RedisCache redisCache;

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
    public String login(LoginBody user) {
        String verifyKey = Constants.CAPTCHA_CODE_IMAGE_KEY + user.getUuid();
        String captcha = redisCache.getCacheObject(verifyKey);

        if (captcha == null) {
            throw new RRException("验证码已失效");
        }
        if (!user.getCode().equalsIgnoreCase(captcha)) {
            throw new RRException("验证码错误");
        }
        Authentication authentication = null;
        try {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                throw new RRException("用户不存在/密码错误");
            } else {
                throw new RRException(e.getMessage());
            }
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 生成token
        return tokenService.createToken(loginUser);
    }

    @Override
    public Users getUserByUsername(String username) {
        return getOne(new QueryWrapper<Users>().lambda().eq(Users::getUsername, username));
    }

    @Override
    public Users getUserByMobile(String mobile) {
        return getOne(new QueryWrapper<Users>().lambda().eq(Users::getMobile, mobile));
    }

    @Override
    public String mobileLogin(String mobile, String code) {
        String verifyKey = Constants.CAPTCHA_CODE_SMS_KEY + mobile;
        ValidateCode captcha = redisCache.getCacheObject(verifyKey);
        System.err.println(captcha.getCode());
        Authentication authentication = null;
        try {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new SmsAuthenticationToken(mobile));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                throw new RRException("用户不存在/密码错误");
            } else {
                throw new RRException(e.getMessage());
            }
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 生成token
        return tokenService.createToken(loginUser);
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
