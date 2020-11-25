package com.imooc.controller.center;

import com.imooc.entitys.Users;
import com.imooc.entitys.bo.center.CenterUserBO;
import com.imooc.service.center.CenterUserService;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.R;
import com.imooc.utils.validator.ValidatorUtils;
import com.imooc.utils.validator.group.AddGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author TryAgain404
 * @mail TryAgain500@163.com
 * @date 2020-11-18 10:55
 */
@Api(value = "用户信息接口", tags = {"用户信息相关接口"})
@RestController
@RequestMapping("/userInfo")
public class CenterUserController {

    private final CenterUserService centerUserService;

    public CenterUserController(CenterUserService centerUserService) {
        this.centerUserService = centerUserService;
    }

    @ApiOperation(value = "修改用户信息", notes = "修改用户信息", httpMethod = "POST")
    @PostMapping("update")
    public R update(@ApiParam(name = "userId", value = "用户id", required = true) @RequestParam String userId,
            @RequestBody CenterUserBO centerUserBO,  HttpServletRequest request, HttpServletResponse response) {

        ValidatorUtils.validateEntity(centerUserBO, AddGroup.class);

        Users userResult = centerUserService.updateUserInfo(userId, centerUserBO);

        setNullProperty(userResult);
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(userResult), true);

        // TODO 后续要改，增加令牌token，会整合进redis，分布式会话

        return R.success();
    }

    @ApiOperation(value = "用户头像修改", notes = "用户头像修改", httpMethod = "POST")
    @PostMapping("uploadFace")
    public R uploadFace(@ApiParam(name = "userId", value = "用户id", required = true) @RequestParam String userId,
            @ApiParam(name = "file", value = "用户头像", required = true) MultipartFile file, HttpServletRequest request, HttpServletResponse response) {

        // 更新用户头像到数据库
        Users userResult = centerUserService.updateUserFace(file, userId);

        setNullProperty(userResult);
        CookieUtils.setCookie(request, response, "user",
                JsonUtils.objectToJson(userResult), true);

        // TODO 后续要改，增加令牌token，会整合进redis，分布式会话

        return R.success();
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
