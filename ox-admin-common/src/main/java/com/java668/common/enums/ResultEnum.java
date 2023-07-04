package com.java668.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Jerry.chen
 * 返回状态码
 * 错误码为字符串类型，共 5 位，分成两个部分：错误产生来源+四位数字编号
 * 第一位 1:系统管理；2:代码生成；...
 * @desc 结果码
 * @date 2023/03/29 18:05
 **/
@Getter
@AllArgsConstructor
public enum ResultEnum implements IResultEnum {

    /**
     * 成功状态码
     */
    SUCCESS(200, "成功"),
    VALIDATE_FAILED(400, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限，请联系系统管理员"),
    /**
     * 失败返回码
     */
    ERROR(500, "服务器繁忙，请稍后重试"),

    /**
     * 失败返回码
     */
    DEMO_SITE_EXCEPTION(10001, "演示站点禁止使用"),
    ;

    private final int code;
    private final String message;

}
