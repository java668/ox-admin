package com.java668.common.exception;

import cn.hutool.core.util.StrUtil;


/**
 * @author Jerry.chen
 */
public class BaseUncheckedException extends RuntimeException {

    private static final long serialVersionUID = -778887391066124051L;

    /**
     * 异常信息
     */
    private String message;

    /**
     * 具体异常码
     */
    private int code;

    public BaseUncheckedException(Throwable cause) {
        super(cause);
    }

    public BaseUncheckedException(final int code, Throwable cause) {
        super(cause);
        this.code = code;
    }


    public BaseUncheckedException(final int code, final String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BaseUncheckedException(final int code, final String message, Throwable cause) {
        super(cause);
        this.code = code;
        this.message = message;
    }

    public BaseUncheckedException(final int code, final String format, Object... args) {
        super(StrUtil.contains(format, "{}") ? StrUtil.format(format, args) : String.format(format, args));
        this.code = code;
        this.message = StrUtil.contains(format, "{}") ? StrUtil.format(format, args) : String.format(format, args);
    }


    @Override
    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
