package com.imooc.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.entitys.UserAddress;
import com.imooc.mapper.UserAddressMapper;
import com.imooc.service.UserAddressService;
import org.springframework.stereotype.Service;

@Service("userAddressService")
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress> implements UserAddressService {


}
