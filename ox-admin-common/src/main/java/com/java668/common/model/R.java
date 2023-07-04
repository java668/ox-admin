package com.java668.common.model;

import com.java668.common.enums.ResultEnum;
import com.java668.common.exception.BizException;
import com.java668.common.utils.JSONUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Jerry.chen
 * @desc 响应结果
 * @date 2023/03/29 18:05
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class R<T> implements Serializable {

    private T data;
    private Integer code;
    private String message;
    private Boolean isSuccess;

    public static <T> R<T> succeed(String message) {
        return of(null, ResultEnum.SUCCESS.getCode(), message, Boolean.TRUE);
    }

    public static <T> R<T> succeed(T model, String message) {
        return of(model, ResultEnum.SUCCESS.getCode(), message, Boolean.TRUE);
    }

    public static <T> R<T> succeed(T model) {
        return of(model, ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), Boolean.TRUE);
    }

    public static <T> R<T> of(T data, Integer code, String message, Boolean isSuccess) {
        return new R<T>(data, code, message, isSuccess);
    }

    public static <T> R<T> failed(String message) {
        return of(null, ResultEnum.INTERNAL_SERVER_ERROR.getCode(), message, Boolean.FALSE);
    }

    public static <T> R<T> failed(T model, String message) {
        return of(model, ResultEnum.INTERNAL_SERVER_ERROR.getCode(), message, Boolean.FALSE);
    }

    public static <T> R<T> failed(ResultEnum resultEnum) {
        return of(null, resultEnum.getCode(), resultEnum.getMessage(), Boolean.FALSE);
    }

    public static <T> R<T> failed(BizException exception) {
        if (exception == null) {
            return failed(ResultEnum.INTERNAL_SERVER_ERROR);
        }
        return of(null, exception.getCode(), exception.getMessage(), Boolean.FALSE);
    }

    public String toJsonString() {
        return JSONUtils.toJsonString(this);
    }
}
