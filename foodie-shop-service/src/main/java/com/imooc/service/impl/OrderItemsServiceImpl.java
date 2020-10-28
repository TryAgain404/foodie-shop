package com.imooc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.entitys.OrderItems;
import com.imooc.mapper.OrderItemsMapper;
import com.imooc.service.OrderItemsService;
import org.springframework.stereotype.Service;


@Service("orderItemsService")
public class OrderItemsServiceImpl extends ServiceImpl<OrderItemsMapper, OrderItems> implements OrderItemsService {



}
