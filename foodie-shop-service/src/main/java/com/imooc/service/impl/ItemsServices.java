package com.imooc.service.impl;

import com.imooc.entitys.Items;
import com.imooc.entitys.ItemsImg;
import com.imooc.entitys.vo.ItemsResultVO;
import com.imooc.service.ItemsImgService;
import com.imooc.service.ItemsParamService;
import com.imooc.service.ItemsService;
import com.imooc.service.ItemsSpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品类相关数据返回接口类
 * @author TryAgain404
 * @date 2020-11-4 14:34
 */
@Service
public class ItemsServices {

    @Autowired
    ItemsService itemsService;
    @Autowired
    ItemsImgService itemsImgService;
    @Autowired
    ItemsSpecService itemsSpecService;
    @Autowired
    ItemsParamService itemsParamService;

    public ItemsResultVO itemsResult(String itemId) {

        return new ItemsResultVO(itemsService.queryItemById(itemId),
                itemsImgService.queryItemImgList(itemId),
                itemsSpecService.queryItemSpecList(itemId),
                itemsParamService.queryItemParam(itemId));
    }
}
