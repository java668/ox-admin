package com.java668.common.exception;

/**
 * @author Jerry.chen
 * @desc 业务异常
 * @date 2023/03/29 18:05
 **/
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 6610083281801529147L;

    public BusinessException(String message) {
        super(message);
    }
}
