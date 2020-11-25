package com.imooc.controller.center;

import com.imooc.entitys.Users;
import com.imooc.service.center.CenterUserService;
import com.imooc.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TryAgain404
 * @mail TryAgain500@163.com
 * @date 2020-11-25 16:56
 */
@RestController
@RequestMapping("center")
@Api(value = "center - 用户中心", tags = {"用户中心展示的相关接口"})
public class CenterController {

    private final CenterUserService centerUserService;

    public CenterController(CenterUserService centerUserService) {
        this.centerUserService = centerUserService;
    }

    @ApiOperation(value = "获取用户信息", notes = "获取用户信息", httpMethod = "GET")
    @GetMapping("userInfo")
    public R userInfo(@ApiParam(name = "userId", value = "用户id", required = true) @RequestParam String userId) {

        Users user = centerUserService.queryUserInfo(userId);

        return R.success(user);
    }
}
