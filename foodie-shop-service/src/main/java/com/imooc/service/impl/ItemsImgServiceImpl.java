package com.imooc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.entitys.ItemsImg;
import com.imooc.mapper.ItemsImgMapper;
import com.imooc.service.ItemsImgService;
import com.imooc.utils.enums.YesOrNo;
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

    @Override
    public String queryItemMainImgById(String itemId) {
        ItemsImg result = getOne(new QueryWrapper<ItemsImg>().lambda().
                          eq(ItemsImg::getItemId, itemId).eq(ItemsImg::getIsMain, YesOrNo.yes.type));
        return result != null ? result.getUrl() : "";
    }
}
