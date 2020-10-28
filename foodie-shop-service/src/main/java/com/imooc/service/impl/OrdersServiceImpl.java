package com.imooc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.entitys.Orders;
import com.imooc.mapper.OrdersMapper;
import com.imooc.service.OrdersService;
import org.springframework.stereotype.Service;



@Service("ordersService")
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {



}
