package com.java668.oxadmin.config.secrity.components;

import com.java668.common.constant.Constants;
import com.java668.common.model.R;
import com.java668.common.model.SysUser;
import com.java668.oxadmin.modules.system.service.ILogRecordEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 退出登录处理器
 *
 * @author jerry.chen
 * @since 2023-03-25 19:55:38
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private final ILogRecordEventService logRecordEventService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        String message = R.success(authentication).toJsonString();
        response.getWriter().write(message);

        Long loginStartTime = (Long) request.getAttribute(Constants.LOG_START_TIME_ATTRIBUTE);
        Long loginDuration = System.currentTimeMillis() - loginStartTime;
        log.info("登录耗时: {}毫秒", loginDuration);
        // 新增退出登录日志
        String username = "";
        if (null != authentication) {
            SysUser principal = (SysUser) authentication.getPrincipal();
            username = principal.getUsername();
        }
        logRecordEventService.pushEvent("认证模块", "退出登录", message, loginDuration, username, Boolean.FALSE);

    }

}
