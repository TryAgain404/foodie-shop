package com.imooc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.entitys.ItemsImg;
import com.imooc.mapper.ItemsImgMapper;
import com.imooc.service.ItemsImgService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author TryAgain404
 */
@Service("itemsImgService")
public class ItemsImgServiceImpl extends ServiceImpl<ItemsImgMapper, ItemsImg> implements ItemsImgService {


    @Override
    public List<ItemsImg> queryItemImgList(String itemId) {
        return this.list(new QueryWrapper<ItemsImg>().lambda().eq(ItemsImg::getItemId, itemId));
    }
}
