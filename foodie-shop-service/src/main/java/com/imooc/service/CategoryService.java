package com.imooc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.entitys.Category;
import com.imooc.entitys.vo.CategoryVO;
import com.imooc.entitys.vo.NewItemsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品分类
 *
 * @author TryAgain404
 * @email TryAgain500@163.com
 * @date 2020-10-21 11:33:24
 */
public interface CategoryService extends IService<Category> {

    /**
     * 获取一级分类
     * @param type 一级类Id
     * @return
     */
    List<Category> getParentNames(Integer type);

    /**
     * 获得各个分类下的最新6个商品
     * @param rootCatId 父分类Id
     * @return
     */
    List<CategoryVO> getSubCatList(Integer rootCatId);

    List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId);
}

