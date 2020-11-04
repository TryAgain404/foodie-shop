package com.imooc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.entitys.ItemsParam;

/**
 * 商品参数
 *
 * @author TryAgain404
 * @email TryAgain500@163.com
 * @date 2020-10-21 11:33:24
 */
public interface ItemsParamService extends IService<ItemsParam> {

    /**
     * 根据商品id查询商品参数
     * @param itemId
     * @return
     */
    ItemsParam queryItemParam(String itemId);

}

