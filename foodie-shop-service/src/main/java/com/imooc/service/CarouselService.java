package com.imooc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.entitys.Carousel;

import java.util.List;

/**
 * 轮播图
 *
 * @author TryAgain404
 * @email TryAgain500@163.com
 * @date 2020-10-21 11:33:24
 */
public interface CarouselService extends IService<Carousel> {

    /**
     * 获取分类
     * @param isShow
     * @return
     */
    List<Carousel> queryList(Integer isShow);

}

