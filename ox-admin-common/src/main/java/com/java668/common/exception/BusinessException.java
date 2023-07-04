package com.java668.common.exception;

import com.java668.common.enums.IResultEnum;
import com.java668.common.enums.ResultEnum;
import lombok.Data;

/**
 * @author Jerry.chen
 * @desc 业务异常
 * @date 2023/03/29 18:05
 **/
@Data
public class BusinessException extends BaseUncheckedException {

    private static final long serialVersionUID = 6610083281801529147L;

    public static final String DEFAULT_MESSAGE = "网络错误，请稍后重试！";

    /**
     * 异常消息
     */
    private String message = DEFAULT_MESSAGE;

    /**
     * 错误码
     */
    private ResultEnum resultCode;

    public BusinessException(String message) {
        super(ResultEnum.ERROR.getCode(), message);
        this.resultCode = ResultEnum.ERROR;
        this.message = message;
    }

    public BusinessException(IResultEnum resultEnum) {
        super(resultEnum.getCode(), resultEnum.getMessage());
        this.resultCode = resultCode;
    }

}
