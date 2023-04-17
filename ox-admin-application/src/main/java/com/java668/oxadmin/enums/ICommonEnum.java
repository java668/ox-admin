package com.java668.oxadmin.enums;

import java.util.EnumSet;
import java.util.Objects;

/**
 * @author jerry.chen
 * @desc 通用枚举接口
 * @date 2022/11/21 16:42
 **/
public interface ICommonEnum<T> {

    /**
     * 获取编码
     *
     * @Param
     * @return T
     **/
    T getCode();

    /**
     * 获取备注
     *
     * @Param
     * @return java.lang.String
     **/
    String getRemark();

    /**
     * 获取枚举
     *
     * @param code
     * @param clazz
     * @return E
     */
    static <E extends Enum<E> & ICommonEnum, T> E getEnmu(T code, Class<E> clazz) {
        Objects.requireNonNull(code);

        EnumSet<E> all = EnumSet.allOf(clazz);
        return all.stream().filter(e -> e.getCode().equals(code)).findFirst().orElse(null);
    }

    /**
     * 校验枚举
     *
     * @param code
     * @param clazz
     * @return boolean
     */
    static <E extends Enum<E> & ICommonEnum, T> boolean isValidCode(T code, Class<E> clazz) {
        Objects.requireNonNull(code);

        return ICommonEnum.getEnmu(code,clazz) != null;
    }

}
