package com.java668.common.model;

/**
 * @author Jerry.chen
 * @desc CodeEnum
 * @date 2023/03/29 18:05
 **/
public enum CodeEnum {
    SUCCESS(200),
    ERROR(500);

    private Integer code;
    CodeEnum(Integer code){
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
