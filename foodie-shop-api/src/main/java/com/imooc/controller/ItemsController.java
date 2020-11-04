package com.imooc.controller;

import com.imooc.entitys.vo.CommentLevelCountsVO;
import com.imooc.entitys.vo.ItemsResultVO;
import com.imooc.service.*;
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

import java.util.HashMap;
import java.util.Map;

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



}
