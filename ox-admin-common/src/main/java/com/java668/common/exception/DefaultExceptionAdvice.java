package com.java668.common.exception;

import com.java668.common.enums.ResultEnum;
import com.java668.common.model.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jerry.chen
 * @desc 异常通用处理
 * @date 2023/03/29 18:05
 **/
@Slf4j
@ResponseBody
public class DefaultExceptionAdvice {

    /**
     * IllegalArgumentException异常处理返回json
     * 返回状态码:400
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class})
    public R badRequestException(IllegalArgumentException e) {
        ResultEnum resultEnum = ResultEnum.BAD_REQUEST;
        return defHandler(resultEnum.getCode(), resultEnum.getMessage(), e);
    }

    /**
     * 返回状态码:405
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public R handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        ResultEnum resultEnum = ResultEnum.METHOD_NOT_ALLOWED;
        return defHandler(resultEnum.getCode(), resultEnum.getMessage(), e);
    }

    /**
     * 返回状态码:415
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public R handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        ResultEnum resultEnum = ResultEnum.UNSUPPORTED_MEDIA_TYPE;
        return defHandler(resultEnum.getCode(), resultEnum.getMessage(), e);
    }

    /**
     * SQLException sql异常处理
     * 返回状态码:500
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({SQLException.class})
    public R handleSQLException(SQLException e) {
        ResultEnum resultEnum = ResultEnum.INTERNAL_SERVER_ERROR;
        return defHandler(resultEnum.getCode(), "服务运行SQLException异常", e);
    }

    /**
     * BusinessException 业务异常处理
     * 返回状态码:500
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BizException.class)
    public R handleException(BizException e) {
        return R.failed(e);
    }

    /**
     * 所有异常统一处理
     * 返回状态码:500
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public R handleException(Exception e) throws Exception {
        if (e instanceof AccessDeniedException) {
            throw e;
        }
        ResultEnum resultEnum = ResultEnum.INTERNAL_SERVER_ERROR;
        return defHandler(resultEnum.getCode(), resultEnum.getMessage(), e);
    }

    /**
     * 参数校验常
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R exceptionHandler2(MethodArgumentNotValidException e) {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        String message = allErrors.stream().map(s -> s.getDefaultMessage()).collect(Collectors.joining(";"));
        return defHandler(ResultEnum.BAD_REQUEST.getCode(), message, e);
    }

    /**
     * 参数校验异常
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public R parameterExceptionHandler(MissingServletRequestParameterException e) {
        String name = e.getParameterName();
        String type = e.getParameterType();
        String value = e.getLocalizedMessage();
        String message = String.format("'%s' should be a valid '%s' and '%s' isn't", name, type, value);
        return defHandler(ResultEnum.BAD_REQUEST.getCode(), message, e);
    }

    /**
     * 参数校验异常
     *
     * @param e
     * @return

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ConstraintViolationException.class})
    public R parameterExceptionHandler(ConstraintViolationException e) {
        log.warn("ConstraintViolationException:", e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        String message = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(";"));
        return defHandler(message, e);
    }*/


    private R defHandler(Integer code, String message, Exception e) {
        log.error(message, e);
        return R.failed(code, message);
    }

}
