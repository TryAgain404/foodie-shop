package com.imooc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.imooc.entitys.Items;
import com.imooc.mapper.ItemsMapper;
import com.imooc.service.ItemsService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


/**
 * @author TryAgain404
 */
@Service("itemsService")
public class ItemsServiceImpl extends ServiceImpl<ItemsMapper, Items> implements ItemsService {


    @Override
    public Items queryItemById(String itemId) {
        return this.getOne(new QueryWrapper<Items>().lambda().eq(Items::getId, itemId));
    }
}
