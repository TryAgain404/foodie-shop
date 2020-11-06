package com.imooc.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.entitys.UserAddress;
import com.imooc.mapper.UserAddressMapper;
import com.imooc.service.UserAddressService;
import com.imooc.utils.StringUtils;
import com.imooc.utils.enums.YesOrNo;
import com.imooc.utils.exception.RRException;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = Exception.class)
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
    @Transactional(rollbackFor = Exception.class)
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
    @Transactional(rollbackFor = Exception.class)
    public void setDefalut(String userId, String addressId) {
        getDeDefalutAddress();
        UserAddress userAddress = getUserAddress(userId, addressId);
        if (StringUtils.isNull(userAddress)) {
            throw new RRException("参数错误。查询地址为空");
        }
        userAddress.setIsDefault(YesOrNo.yes.type);
        userAddress.setUpdatedTime(new Date());
        updateById(userAddress);
    }

    @Override
    public UserAddress getUserAddress(String userId, String addressId) {
        return getOne(new QueryWrapper<UserAddress>().lambda().
                eq(UserAddress::getUserId, userId).eq(UserAddress::getId, addressId));
    }

    /**
     * 查询地址列表是否有多个默认地址并设置为0
     */
    private void getDeDefalutAddress() {
        List<UserAddress> list = list(new QueryWrapper<UserAddress>().lambda()
                .eq(UserAddress::getIsDefault, YesOrNo.yes.type));

        if (StringUtils.isNotNull(list)) {
            list.forEach((x) -> {
                x.setIsDefault(YesOrNo.no.type);
                this.updateById(x);
            });
        }
    }
}
