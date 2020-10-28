package com.imooc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.entitys.Category;
import com.imooc.mapper.CategoryMapper;
import com.imooc.service.CategoryService;
import org.springframework.stereotype.Service;



@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {



}
