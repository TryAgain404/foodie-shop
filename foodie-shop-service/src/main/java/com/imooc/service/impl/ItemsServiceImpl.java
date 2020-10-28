package com.imooc.service.impl;

import com.imooc.entitys.Items;
import com.imooc.mapper.ItemsMapper;
import com.imooc.service.ItemsService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("itemsService")
public class ItemsServiceImpl extends ServiceImpl<ItemsMapper, Items> implements ItemsService {


}
