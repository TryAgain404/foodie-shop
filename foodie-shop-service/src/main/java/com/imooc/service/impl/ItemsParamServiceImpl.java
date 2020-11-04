package com.imooc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.imooc.entitys.ItemsParam;
import com.imooc.mapper.ItemsParamMapper;
import com.imooc.service.ItemsParamService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;



@Service("itemsParamService")
public class ItemsParamServiceImpl extends ServiceImpl<ItemsParamMapper, ItemsParam> implements ItemsParamService {


    @Override
    public ItemsParam queryItemParam(String itemId) {
        return getOne(new QueryWrapper<ItemsParam>().lambda().eq(ItemsParam::getItemId, itemId));
    }
}
