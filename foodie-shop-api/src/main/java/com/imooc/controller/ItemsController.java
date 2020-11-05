package com.imooc.controller;

import com.imooc.entitys.vo.CommentLevelCountsVO;
import com.imooc.entitys.vo.ItemsResultVO;
import com.imooc.entitys.vo.ShopCartVO;
import com.imooc.service.ItemsCommentsService;
import com.imooc.service.impl.ItemsServices;
import com.imooc.utils.PageUtils;
import com.imooc.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author TryAgain404
 * @date 2020-11-4 11:14
 */
@Slf4j
@RestController
@RequestMapping("/items")
@Api(value = "商品接口", tags = {"商品相关接口"})
public class ItemsController {
    @Autowired
    ItemsServices services;
    @Autowired
    ItemsCommentsService itemsCommentsService;

    @GetMapping("/info/{itemId}")
    @ApiOperation(value = "获取商品详情", notes = "获取商品详情", httpMethod = "GET")
    public R sixNewItems(@ApiParam(name = "itemId", value = "商品ID", required = true)
                         @PathVariable("itemId") String itemId) {
        if (StringUtils.isEmpty(itemId)) {
            return R.error("商品不存在");
        }

        ItemsResultVO itemsResultVO = services.itemsResult(itemId);

        return R.success(itemsResultVO);
    }

    @GetMapping("/commentLevel")
    @ApiOperation(value = "获取评论数量", notes = "获取评论数量", httpMethod = "GET")
    public R commentLevel(@ApiParam(name = "itemId", value = "评论数量", required = true)
                         @RequestParam String itemId) {

        CommentLevelCountsVO commentLevel = itemsCommentsService.getCommentLevel(itemId);
        return R.success(commentLevel);
    }


    @GetMapping("/comments")
    @ApiOperation(value = "获取商品评论", notes = "获取商品评论", httpMethod = "GET")
    public R comments(@ApiParam(name = "itemId", value = "商品ID", required = true)
                      @RequestParam("itemId") String itemId,
                      @ApiParam(name = "level", value = "评论等级", required = true)
                      @RequestParam("level") Integer level,
                      @ApiParam(name = "pages", value = "当前页", required = true)
                      @RequestParam("page") Integer page,
                      @ApiParam(name = "pageSize", value = "页面显示数量", required = true)
                      @RequestParam("pageSize") Integer pageSize) {


        PageUtils pages = itemsCommentsService.queryPage(itemId, level, page, pageSize);

        return R.success(pages);
    }

    @GetMapping("/search")
    @ApiOperation(value = "搜索商品", notes = "搜索商品", httpMethod = "GET")
    public R search(@ApiParam(name = "keywords", value = "关键词", required = true)
                      @RequestParam("keywords") String keywords,
                      @ApiParam(name = "sort", value = "排序", required = true)
                      @RequestParam("sort") String sort,
                      @ApiParam(name = "pages", value = "当前页", required = true)
                      @RequestParam("pages") Integer page,
                      @ApiParam(name = "pageSize", value = "页面显示数量", required = true)
                      @RequestParam("pageSize") Integer pageSize) {

        PageUtils pages = itemsCommentsService.searchPage(keywords, sort, page, pageSize);

        return R.success(pages);
    }

    @GetMapping("/catItems")
    @ApiOperation(value = "根据商品ID搜索商品", notes = "根据商品ID搜索商品", httpMethod = "GET")
    public R catItems(@ApiParam(name = "catId", value = "商品ID", required = true)
                    @RequestParam("catId") String catId,
                    @ApiParam(name = "sort", value = "排序", required = true)
                    @RequestParam("sort") String sort,
                    @ApiParam(name = "pages", value = "当前页", required = true)
                    @RequestParam("pages") Integer page,
                    @ApiParam(name = "pageSize", value = "页面显示数量", required = true)
                    @RequestParam("pageSize") Integer pageSize) {

        PageUtils pages = itemsCommentsService.searchByThirdCatPage(catId, sort, page, pageSize);

        return R.success(pages);
    }


    @ApiOperation(value = "根据商品规格ids查找最新的商品数据", notes = "根据商品规格ids查找最新的商品数据", httpMethod = "GET")
    @GetMapping("/refresh")
    public R refresh(
            @ApiParam(name = "itemSpecIds", value = "拼接的规格ids", required = true, example = "1001,1003,1005")
            @RequestParam String itemSpecIds) {

        if (StringUtils.isEmpty(itemSpecIds)) {
            return R.error("");
        }

        List<ShopCartVO> list = itemsCommentsService.queryItemsBySpecIds(itemSpecIds);

        return R.success(list);
    }


}
