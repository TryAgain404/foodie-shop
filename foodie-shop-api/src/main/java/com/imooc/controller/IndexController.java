package com.imooc.controller;

import com.imooc.entitys.Carousel;
import com.imooc.entitys.Category;
import com.imooc.entitys.vo.CategoryVO;
import com.imooc.entitys.vo.NewItemsVO;
import com.imooc.service.CarouselService;
import com.imooc.service.CategoryService;
import com.imooc.utils.R;
import com.imooc.utils.enums.YesOrNo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author TryAgain404
 * @date 2020-10-22 15:39
 */
@Slf4j
@RestController
@RequestMapping("/index")
@Api(value = "首页", tags = {"首页相关接口"})
public class IndexController {

    @Autowired
    CarouselService carouselService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/carousel")
    @ApiOperation(value = "获取轮播图", notes = "获取轮播图", httpMethod = "GET")
    public R carousel() {
        List<Carousel> carousels = carouselService.queryList(YesOrNo.yes.type);
        return R.success(carousels);
    }

    @GetMapping("/cats")
    @ApiOperation(value = "获取商品分类(一级分类)", notes = "获取商品分类(一级分类)", httpMethod = "GET")
    public R cats() {
        List<Category> categories = categoryService.getParentNames(YesOrNo.yes.type);
        return R.success(categories);
    }

    @GetMapping("/subCat/{rootCatId}")
    @ApiOperation(value = "获取商品子分类", notes = "获取商品子分类", httpMethod = "GET")
    public R subCat(@ApiParam(name = "rootCatId", value = "一级分类id", required = true)
                    @PathVariable("rootCatId") Integer rootCatId) {
        if (rootCatId == null) {
            return R.error("分类不存在");
        }
        List<CategoryVO> categories = categoryService.getSubCatList(rootCatId);
        return R.success(categories);
    }

    @GetMapping("/sixNewItems/{rootCatId}")
    @ApiOperation(value = "获得各个分类下的最新6个商品", notes = "获得各个分类下的最新6个商品", httpMethod = "GET")
    public R sixNewItems(@ApiParam(name = "rootCatId", value = "一级分类id", required = true)
                    @PathVariable("rootCatId") Integer rootCatId) {
        if (rootCatId == null) {
            return R.error("分类不存在");
        }
        List<NewItemsVO> categories = categoryService.getSixNewItemsLazy(rootCatId);
        return R.success(categories);
    }

}
