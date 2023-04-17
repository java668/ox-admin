package com.java668.oxadmin.config;

import com.java668.common.exception.DefaultExceptionAdvice;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * 全局异常处理
 *
 * @author jerry.chen
 * @since 2023-03-25 19:55:38
 */
@ControllerAdvice
public class ExceptionAdvice extends DefaultExceptionAdvice {
}
