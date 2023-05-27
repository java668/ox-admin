package com.java668.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Jerry.chen
 * @desc 结果码
 * @date 2023/03/29 18:05
 **/
@Getter
@AllArgsConstructor
public enum  ResultCodeEnum {

    /**
     * 成功状态码
     */
    SUCCESS(200, "成功"),

    /**
     * 失败返回码
     */
    ERROR(500, "服务器繁忙，请稍后重试"),

    /**
     * 失败返回码
     */
    DEMO_SITE_EXCEPTION(4001, "演示站点禁止使用"),
    ;

    private final Integer code;
    private final String message;
}
