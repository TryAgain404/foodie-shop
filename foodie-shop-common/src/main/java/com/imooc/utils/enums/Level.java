package com.imooc.utils.enums;

/**
 * @author TryAgain404
 * @date 2020-11-3 15:47
 */
public enum Level {

    GOOD(1, "好评"),
    NORMA(2, "中评"),
    BAD(3, "差评");

    public final Integer type;
    public final String value;

    Level(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
