package com.imooc.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.entitys.UserAddress;
import com.imooc.entitys.bo.UserAddressBO;
import com.imooc.mapper.UserAddressMapper;
import com.imooc.service.UserAddressService;
import com.imooc.utils.StringUtils;
import com.imooc.utils.enums.YesOrNo;
import com.imooc.utils.exception.RRException;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author TryAgain404
 */
@Service("userAddressService")
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress> implements UserAddressService {

    @Autowired
    private Sid sid;

    @Override
    public List<UserAddress> getAddressList(String userId) {

        return list(new QueryWrapper<UserAddress>().lambda().eq(UserAddress::getUserId, userId));
    }

    @Override
    public void addUserAddress(UserAddress userAddress) {
        List<UserAddress> addressList = getAddressList(userAddress.getUserId());
        if (addressList.size() > 0) {
            userAddress.setIsDefault(YesOrNo.yes.type);
        }
        userAddress.setId(sid.nextShort());
        userAddress.setCreatedTime(new Date());
        userAddress.setUpdatedTime(new Date());
        userAddress.setIsDefault(YesOrNo.no.type);
        save(userAddress);
    }

    @Override
    public void delUserAddress(String userId, String addressId) {
        remove(new QueryWrapper<UserAddress>().lambda().
                eq(UserAddress::getUserId, userId).eq(UserAddress::getId, addressId));
    }

    @Override
    public void updateUserAddress(UserAddress newUserAddress) {
        UserAddress userAddress = getUserAddress(newUserAddress.getUserId(), newUserAddress.getId());
        if (StringUtils.isNull(userAddress)) {
            throw new RRException("参数错误。查询地址为空");
        }
        newUserAddress.setUpdatedTime(new Date());
        newUserAddress.setIsDefault(YesOrNo.yes.type);
        BeanUtils.copyProperties(newUserAddress, userAddress);

        updateById(userAddress);

    }

    @Override
    public void setDefalut(String userId, String addressId) {
        List<UserAddress> deDefalutAddress = getDeDefalutAddress();
        if (StringUtils.isNotNull(deDefalutAddress)) {
            for (UserAddress userAddress : deDefalutAddress) {
                userAddress.setIsDefault(YesOrNo.no.type);
                this.updateById(userAddress);
            }
        }
        UserAddress userAddress = getUserAddress(userId, addressId);
        if (StringUtils.isNull(userAddress)) {
            throw new RRException("参数错误。查询地址为空");
        }
        userAddress.setIsDefault(YesOrNo.yes.type);
        userAddress.setUpdatedTime(new Date());
        updateById(userAddress);
    }

    private UserAddress getUserAddress(String userId, String addressId) {
        return getOne(new QueryWrapper<UserAddress>().lambda().
                eq(UserAddress::getUserId, userId).eq(UserAddress::getId, addressId));
    }

    private List<UserAddress> getDeDefalutAddress() {
        return list(new QueryWrapper<UserAddress>().lambda().eq(UserAddress::getIsDefault, YesOrNo.yes.type));
    }
}
