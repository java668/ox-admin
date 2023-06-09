package com.java668.oxadmin.config.secrity.components;

import com.java668.common.model.R;
import com.java668.oxadmin.modules.system.service.ILogRecordEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理器
 *
 * @author jerry.chen
 * @since 2023-03-25 19:55:38
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final ILogRecordEventService logRecordEventService;

    /**
     * @param exception 认证失败时抛出异常
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        if (exception instanceof InternalAuthenticationServiceException) {
            log.error("登录系统内部错误：", exception);
            response.getWriter().write(R.failed("系统繁忙，请稍候重试").toJsonString());
            logRecordEventService.pushEvent("登录系统内部错误");
        } else {
            response.getWriter().write(R.failed(exception.getMessage()).toJsonString());
            logRecordEventService.pushEvent(exception.getMessage());
        }
        // 登陆认证失败新增日志
    }
}
