package com.imooc.entitys.vo;

import lombok.Data;

import java.util.List;

/**
 * @author TryAgain404
 * @date 2020-11-3 17:12
 */
@Data
public class CategoryVO {

    private Integer id;
    private String name;
    private String type;
    private Integer fatherId;
    /**
     * 三级分类vo list
     */
    private List<SubCategoryVO> subCatList;
}
