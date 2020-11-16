package com.imooc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.entitys.ItemsSpec;
import com.imooc.mapper.ItemsSpecMapper;
import com.imooc.service.ItemsSpecService;
import com.imooc.utils.exception.RRException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author TryAgain404
 */
@Service("itemsSpecService")
public class ItemsSpecServiceImpl extends ServiceImpl<ItemsSpecMapper, ItemsSpec> implements ItemsSpecService {


    @Override
    public List<ItemsSpec> queryItemSpecList(String itemId) {
        return list(new QueryWrapper<ItemsSpec>().lambda().eq(ItemsSpec::getItemId, itemId));
    }

    @Override
    public ItemsSpec queryItemSpecById(String itemSpecId) {
        return getById(itemSpecId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void decreaseItemSpecStock(String itemSpecId, int buyCounts) {
        int result = baseMapper.decreaseItemSpecStock(itemSpecId, buyCounts);
        if (result != 1) {
            throw new RRException("订单创建失败，原因：库存不足!");
        }
    }
}
