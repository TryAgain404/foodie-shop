package com.imooc.service.impl;

import com.imooc.entitys.Carousel;
import com.imooc.mapper.CarouselMapper;
import com.imooc.service.CarouselService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("carouselService")
public class CarouselServiceImpl extends ServiceImpl<CarouselMapper, Carousel> implements CarouselService {


}
