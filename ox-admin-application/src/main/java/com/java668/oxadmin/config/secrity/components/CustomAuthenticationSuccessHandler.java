package com.java668.oxadmin.config.secrity.components;

import com.java668.common.constant.Constants;
import com.java668.common.model.R;
import com.java668.common.utils.AuthUtils;
import com.java668.oxadmin.modules.system.service.ILogRecordEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证成功处理器
 *
 * @author jerry.chen
 * @since 2023-03-25 19:55:38
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final ILogRecordEventService logRecordEventService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        String message = R.success(authentication, "认证成功").toJsonString();
        response.getWriter().write(message);
        Long loginStartTime = (Long) request.getAttribute(Constants.LOG_START_TIME_ATTRIBUTE);
        Long loginDuration = System.currentTimeMillis() - loginStartTime;
        log.info("登录耗时: {}毫秒", loginDuration);
        // 新增登录日志
        logRecordEventService.pushEvent("认证模块", "用户登录", message, loginDuration, AuthUtils.getUsername(), Boolean.FALSE);
    }
}
