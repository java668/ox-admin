package com.java668.common.exception;

import com.java668.common.enums.IResultEnum;
import com.java668.common.enums.ResultEnum;

/**
 * @author Jerry.chen
 * @desc 业务异常
 * @date 2023/03/29 18:05
 **/
public class BizException extends BaseUncheckedException {

    private static final long serialVersionUID = 6610083281801529147L;

    public BizException(String message) {
        super(ResultEnum.INTERNAL_SERVER_ERROR.getCode(), message);
    }


    public BizException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public BizException(IResultEnum resultEnum) {
        super(resultEnum.getCode(), resultEnum.getMessage());
    }

}
