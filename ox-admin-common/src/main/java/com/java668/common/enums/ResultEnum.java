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
     * 基础响应结果码
     */
    SUCCESS(200, "成功"),
    BAD_REQUEST(400, "参数解析失败"),
    UNAUTHORIZED(401, "未认证"),
    FORBIDDEN(403, "未授权"),
    METHOD_NOT_ALLOWED(405, "不支持当前请求方法"),
    UNSUPPORTED_MEDIA_TYPE(415, "媒体格式类型不支持"),
    INTERNAL_SERVER_ERROR(500, "服务器繁忙，请稍后重试"),

    /**
     * 系统管理 业务异常错误码
     */
    SYSTEM_DEMO_SITE_EXCEPTION(10001, "演示站点禁止使用"),



    /**
     * 代码生成 业务异常错误码
     */
    GEN_EXCEPTION(20001, "代码生成 异常错误码"),
    ;

    private final int code;
    private final String message;

}
