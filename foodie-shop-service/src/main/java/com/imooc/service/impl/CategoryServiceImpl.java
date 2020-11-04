package com.imooc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.entitys.Category;
import com.imooc.entitys.vo.CategoryVO;
import com.imooc.entitys.vo.NewItemsVO;
import com.imooc.mapper.CategoryMapper;
import com.imooc.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * @author TryAgain404
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {


    @Override
    public List<Category> getParentNames(Integer type) {
        return list(new QueryWrapper<Category>().lambda().eq(Category::getType, type));
    }

    @Override
    public List<CategoryVO> getSubCatList(Integer rootCatId) {

        return baseMapper.getSubCatList(rootCatId);
    }

    @Override
    public List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId) {
        return baseMapper.getSixNewItemsLazy(rootCatId);
    }
}
