package com.imooc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.entitys.UserAddress;

import java.util.List;

/**
 * 用户地址表
 *
 * @author TryAgain404
 * @email TryAgain500@163.com
 * @date 2020-10-21 11:33:24
 */
public interface UserAddressService extends IService<UserAddress> {

    /**
     * 获取用户地址列表
     * @param userId 用户Id
     * @return 用户地址列表
     */
    List<UserAddress> getAddressList(String userId);
    /**
     * 新增用户地址
     * @param userAddress 用户地址
     */
    void addUserAddress(UserAddress userAddress);
    /**
     * 删除用户地址
     * @param userId 用户Id
     * @param addressId 地址Id
     */
    void delUserAddress(String userId, String addressId);
    /**
     * 更新用户地址
     * @param userAddress 用户地址的BO
     */
    void updateUserAddress(UserAddress userAddress);

    /**
     * 设置默认地址
     * @param userId 用户Id
     * @param addressId 地址Id
     */
    void setDefalut(String userId, String addressId);
}

