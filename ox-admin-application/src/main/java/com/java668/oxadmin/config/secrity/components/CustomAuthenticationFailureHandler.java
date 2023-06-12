package com.java668.oxadmin.config.secrity.components;

import com.java668.common.constant.Constants;
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
        } else {
            response.getWriter().write(R.failed(exception.getMessage()).toJsonString());
        }
        // 登陆认证失败新增日志
        Long loginStartTime = (Long) request.getAttribute(Constants.LOG_START_TIME_ATTRIBUTE);
        Long loginDuration = System.currentTimeMillis() - loginStartTime;
        log.info("登录耗时: {}毫秒 错误描述: {}", loginDuration, exception.getMessage());
        String username = request.getParameter("username");
        logRecordEventService.pushEvent("认证授权", "用户登录", exception.getMessage(), loginDuration, username, Boolean.TRUE);
    }
}
