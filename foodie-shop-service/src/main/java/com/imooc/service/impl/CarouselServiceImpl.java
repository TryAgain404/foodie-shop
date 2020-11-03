package com.imooc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.imooc.entitys.Carousel;
import com.imooc.mapper.CarouselMapper;
import com.imooc.service.CarouselService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;


/**
 * @author TryAgain404
 */
@Service("carouselService")
public class CarouselServiceImpl extends ServiceImpl<CarouselMapper, Carousel> implements CarouselService {


    @Override
    public List<Carousel> queryList(Integer isShow) {
        return this.list(new QueryWrapper<Carousel>().lambda().eq(Carousel::getIsShow, isShow)
               .orderByAsc(Carousel::getSort));

    }
}
