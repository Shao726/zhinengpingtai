package com.dite.znpt.monitor.utils;

import com.dite.znpt.monitor.constant.dict.ValueAndLabel;

import java.util.Arrays;

/**
 * 字典工具类
 *
 * @author huise23
 * @since 2023-07-28 15:38:41
 */
public class DictUtils {

    /**
     * 通用方法，根据传入的枚举类和value返回对应的label值
     *
     * @param enumClass 枚举类
     * @param value 值
     * @return {@link String }
     * @author huise23
     * @since 2023-07-28 15:40:07
     */
    public static <T extends Enum<T> & ValueAndLabel> String getDictLabel(Class<T> enumClass, String value) {
        final String label = Arrays.stream(enumClass.getEnumConstants())
                .filter(enumItem -> enumItem.getValue().equals(value))
                .map(t->t.getLabel())
                .findFirst()
                .orElse(null);
        return label;
    }

}
