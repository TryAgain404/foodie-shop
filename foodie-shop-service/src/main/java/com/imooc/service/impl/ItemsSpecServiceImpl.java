package com.imooc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.imooc.entitys.ItemsSpec;
import com.imooc.mapper.ItemsSpecMapper;
import com.imooc.service.ItemsSpecService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("itemsSpecService")
public class ItemsSpecServiceImpl extends ServiceImpl<ItemsSpecMapper, ItemsSpec> implements ItemsSpecService {


    @Override
    public List<ItemsSpec> queryItemSpecList(String itemId) {
        return list(new QueryWrapper<ItemsSpec>().lambda().eq(ItemsSpec::getItemId, itemId));
    }
}
