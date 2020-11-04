package com.imooc.mapper;

import com.imooc.entitys.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imooc.entitys.vo.CategoryVO;
import com.imooc.entitys.vo.NewItemsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品分类 
 *
 * @author TryAgain404
 * @email TryAgain500@163.com
 * @date 2020-10-21 11:33:24
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 获取二级分类及三级分类
     * @param rootCatId
     * @return
     */
    List<CategoryVO> getSubCatList(@Param("rootCatId") Integer rootCatId);

    /**
     * 获取最新的六个商品
     * @param rootCatId
     * @return
     */
    List<NewItemsVO> getSixNewItemsLazy(@Param("rootCatId") Integer rootCatId);

}
