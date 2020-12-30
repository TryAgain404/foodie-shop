package com.imooc.controller;

import com.imooc.entitys.SysMenu;
import com.imooc.entitys.security.LoginBody;
import com.imooc.entitys.security.LoginUser;
import com.imooc.service.UsersService;
import com.imooc.utils.Constants;
import com.imooc.utils.R;
import com.imooc.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * 登录验证
 * @author TryAgain404
 */
@RestController
public class SysLoginController {
    @Autowired
    private UsersService usersService;

    @PostMapping("/login")
    public R login(@RequestBody LoginBody loginBody) {
        // 生成令牌
        String token = usersService.login(loginBody);
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", token);
        return R.success(map);
    }
}
