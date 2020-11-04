package com.imooc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.entitys.ItemsImg;

import java.util.List;

/**
 * 商品图片
 *
 * @author TryAgain404
 * @email TryAgain500@163.com
 * @date 2020-10-21 11:33:24
 */
public interface ItemsImgService extends IService<ItemsImg> {

    /**
     * 根据商品id查询商品图片列表
     * @param itemId
     * @return
     */
     List<ItemsImg> queryItemImgList(String itemId);

}

