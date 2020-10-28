package com.imooc.controller;

import com.imooc.entitys.BO.UsnerBO;
import com.imooc.entitys.Users;
import com.imooc.service.UsersService;
import com.imooc.utils.R;
import com.imooc.utils.validator.Assert;
import com.imooc.utils.validator.ValidatorUtils;
import com.imooc.utils.validator.group.AddGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;

/**
 * @author TryAgain404
 * @date 2020-10-22 15:39
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UsersService usersService;

    @GetMapping("/getuser")
    public R getUser(String username) {

        return R.success(usersService.getUsernameIsExit(username));
    }

    @PostMapping("/save")
    public R save(@RequestBody UsnerBO users) {
        ValidatorUtils.validateEntity(users, AddGroup.class);
        if (!users.getPassword().equals(users.getConfirmPassword())) {
            return R.error("两次密码不正确");
        }
        usersService.saveUser(users);
        return R.success();
    }

    @GetMapping("/delete/{id}")
    @ApiOperation("删除用户")
    public R delete(@PathVariable("id") String id) {
        Assert.isBlank(id, "用户Id不能为空");
        usersService.deleteUser(id);
        return R.success();
    }

    @PostMapping("/update")
    public R update(@RequestBody Users user) {
        ValidatorUtils.validateEntity(user, AddGroup.class);
        user.setUpdatedTime(new Date());
        usersService.update(user);
        return R.success();
    }

}
