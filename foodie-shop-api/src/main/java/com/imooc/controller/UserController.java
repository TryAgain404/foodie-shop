package com.imooc.controller;

import com.imooc.entitys.bo.UserBO;
import com.imooc.entitys.Users;
import com.imooc.service.UsersService;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.R;
import com.imooc.utils.exception.RRException;
import com.imooc.utils.validator.Assert;
import com.imooc.utils.validator.ValidatorUtils;
import com.imooc.utils.validator.group.AddGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author TryAgain404
 * @date 2020-10-22 15:39
 */
@Slf4j
@RestController
@RequestMapping("/user")
@Api(value = "用户的相关接口", tags = {"用户的相关接口"})
public class UserController {

    static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UsersService usersService;

    @GetMapping("/usernameIsExist")
    @ApiOperation(value = "获取用户信息", notes = "获取用户信息", httpMethod = "GET")
    public R getUser(@RequestParam("username") String username) {
        if (username == null) {
            return R.error("用户名不能为空");
        }
        boolean usernameIsExit = usersService.getUsernameIsExit(username);
        if (!usernameIsExit) {
            return R.error("用户名已经存在");
        }
        return R.success();
    }

    @PostMapping("/save")
    @ApiOperation(value = "创建用户", notes = "创建用户", httpMethod = "POST")
    public R regist(@RequestBody UserBO users, HttpServletRequest request, HttpServletResponse response) {
        ValidatorUtils.validateEntity(users, AddGroup.class);
        if (!users.getPassword().equals(users.getConfirmPassword())) {
            return R.error("两次密码不正确");
        }
        Users userResult = usersService.saveUser(users);
        CookieUtils.setCookie(request, response, "user",
                JsonUtils.objectToJson(userResult), true);
        return R.success();
    }

    @GetMapping("/delete/{id}")
    @ApiOperation(value = "删除用户", notes = "删除用户", httpMethod = "GET")
    public R delete(@PathVariable("id") String id) {
        Assert.isBlank(id, "用户Id不能为空");
        usersService.deleteUser(id);
        return R.success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新用户资料", notes = "更新用户资料", httpMethod = "POST")
    public R update(@RequestBody Users user) {
        ValidatorUtils.validateEntity(user, AddGroup.class);
        user.setUpdatedTime(new Date());
        usersService.update(user);
        return R.success();
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
    public R login(@RequestBody UserBO user, HttpServletRequest request, HttpServletResponse response) {
        ValidatorUtils.validateEntity(user, AddGroup.class);
        Users userResult = usersService.login(user);
        CookieUtils.setCookie(request, response, "user",
                JsonUtils.objectToJson(userResult), true);
        return R.success();
    }


    @GetMapping("/logout/{userId}")
    @ApiOperation(value = "用户退出登录", notes = "用户退出登录", httpMethod = "GET")
    public R logout(@PathVariable(value = "userId") String userId, HttpServletRequest request,
                    HttpServletResponse response
                    ) {
        if (userId == null || "".equals(userId)) {
            throw new RRException("用户Id不合法");
        }

        logger.error("用户退出成功");
        CookieUtils.deleteCookie(request, response, "user");

        return R.success("退出成功");
    }

}
