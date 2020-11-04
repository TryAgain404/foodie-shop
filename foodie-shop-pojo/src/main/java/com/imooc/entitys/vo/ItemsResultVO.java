package com.imooc.entitys.vo;

import com.imooc.entitys.Items;
import com.imooc.entitys.ItemsImg;
import com.imooc.entitys.ItemsParam;
import com.imooc.entitys.ItemsSpec;
import lombok.Data;
import java.util.List;


/**
 * @author TryAgain404
 * @date 2020-11-4 11:21
 */
@Data
public class ItemsResultVO {
    private Items item;
    private List<ItemsImg> itemImgList;
    private List<ItemsSpec> itemSpecList;
    private ItemsParam itemParams;

    public ItemsResultVO(Items item, List<ItemsImg> itemImgList, List<ItemsSpec> itemSpecList,
                         ItemsParam itemParams) {
        this.item = item;
        this.itemImgList = itemImgList;
        this.itemSpecList = itemSpecList;
        this.itemParams = itemParams;
    }
}
