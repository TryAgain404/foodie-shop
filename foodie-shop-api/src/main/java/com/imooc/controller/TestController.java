package com.imooc.controller;

import com.imooc.entitys.Users;
import com.imooc.service.UsersService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TryAgain404
 * @date 2020-10-21 10:24
 */
@RestController
public class TestController {

    @Autowired
    UsersService usersService;

    @GetMapping("/hello")
    public Object hello() {

        return "Hello World~";
    }

    @GetMapping("/getusers")
    public Users getUser() {

        return usersService.getById(1);
    }
}
