package com.imooc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.entitys.Items;

/**
 * @author TryAgain404
 * @date 2020-11-4 14:22
 */
public interface ItemsService extends IService<Items> {

    /**
     * 根据商品ID查询详情
     * @param itemId
     * @return
     */
    Items queryItemById(String itemId);
}
