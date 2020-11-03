package com.imooc.utils.enums;

/**
 * @author TryAgain404
 * @date 2020-11-3 15:47
 */
public enum YesOrNo {

    no(0, "否"),
    yes(1, "是"),;

    public final Integer type;
    public final String value;

    YesOrNo(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
