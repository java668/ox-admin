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
public interface IResultEnum {

    /**
     * 异常编码
     *
     * @return 异常编码
     */
    int getCode();

    /**
     * 异常消息
     *
     * @return 异常消息
     */
    String getMessage();

}
