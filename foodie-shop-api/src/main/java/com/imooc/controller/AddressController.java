package com.imooc.controller;

import com.imooc.entitys.UserAddress;
import com.imooc.service.UserAddressService;
import com.imooc.utils.R;
import com.imooc.utils.exception.RRException;
import com.imooc.utils.validator.ValidatorUtils;
import com.imooc.utils.validator.group.AddGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author TryAgain404
 * @date 2020-10-22 15:39
 */
@RestController
@RequestMapping("/address")
@Api(value = "地址相关接口", tags = {"地址相关接口"})
public class AddressController {

    @Autowired
    UserAddressService userAddressService;

    @PostMapping("/list")
    @ApiOperation(value = "获取用户地址列表", notes = "获取用户地址列表", httpMethod = "POST")
    public R list(@RequestParam("userId") String userId) {
        if (userId == null || StringUtils.isEmpty(userId)) {
            return R.error("用户名不能为空");
        }

        List<UserAddress> list = userAddressService.getAddressList(userId);

        return R.success(list);
    }

    @PostMapping("/add")
    @ApiOperation(value = "用户新增地址", notes = "用户新增地址", httpMethod = "POST")
    public R add(@RequestBody UserAddress userAddress) {

        ValidatorUtils.validateEntity(userAddress, AddGroup.class);
        userAddressService.addUserAddress(userAddress);

        return R.success();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除用户地址", notes = "删除用户地址", httpMethod = "POST")
    public R delete(@RequestParam("userId") String userId,
                    @RequestParam("addressId") String addressId) {

        if (StringUtils.isEmpty(userId) || "".equals(userId ) ||
            StringUtils.isEmpty(addressId) || "".equals(addressId)) {
            throw new RRException("");
        }

        userAddressService.delUserAddress(userId, addressId);

        return R.success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "删除用户地址", notes = "删除用户地址", httpMethod = "POST")
    public R update(@RequestBody UserAddress userAddress) {

        ValidatorUtils.validateEntity(userAddress, AddGroup.class);

        userAddressService.updateUserAddress(userAddress);

        return R.success();
    }

    @PostMapping("/setDefalut")
    @ApiOperation(value = "设置默认地址", notes = "设置默认地址", httpMethod = "POST")
    public R setDefalut(@RequestParam("userId") String userId, @RequestParam("addressId") String addressId) {

        if (StringUtils.isEmpty(userId) || "".equals(userId ) ||
                StringUtils.isEmpty(addressId) || "".equals(addressId)) {
            throw new RRException("");
        }

        userAddressService.setDefalut(userId, addressId);

        return R.success();
    }

}
