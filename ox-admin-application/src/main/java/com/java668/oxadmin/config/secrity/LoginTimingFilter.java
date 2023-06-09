package com.java668.oxadmin.config.secrity;

import org.springframework.util.StopWatch;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Jerry.chen
 */
public class LoginTimingFilter implements Filter {

    private static final String LOGIN_START_TIME_ATTRIBUTE = "loginStartTime";
    private static final String LOGOUT_START_TIME_ATTRIBUTE = "logoutStartTime";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        StopWatch sw = new StopWatch();
        // 在登录请求之前记录开始时间
        if (requestURI.equals("/login")) {
            sw.start(LOGIN_START_TIME_ATTRIBUTE);
            request.setAttribute(LOGIN_START_TIME_ATTRIBUTE, System.currentTimeMillis());
        }
        // 在退出请求之前记录开始时间
        else if (requestURI.equals("/logout")) {
            request.setAttribute(LOGOUT_START_TIME_ATTRIBUTE, System.currentTimeMillis());
        }

        chain.doFilter(request, response);

        // 在登录请求完成后计算耗时
        if (requestURI.equals("/login")) {
            Long loginStartTime = (Long) request.getAttribute(LOGIN_START_TIME_ATTRIBUTE);
            if (loginStartTime != null) {
                long loginEndTime = System.currentTimeMillis();
                long loginDuration = loginEndTime - loginStartTime;
                System.out.println("登录耗时: " + loginDuration + "毫秒");
            }
        }
        // 在退出请求完成后计算耗时
        else if (requestURI.equals("/logout")) {
            Long logoutStartTime = (Long) request.getAttribute(LOGOUT_START_TIME_ATTRIBUTE);
            if (logoutStartTime != null) {
                long logoutEndTime = System.currentTimeMillis();
                long logoutDuration = logoutEndTime - logoutStartTime;
                System.out.println("退出耗时: " + logoutDuration + "毫秒");
            }
        }
    }

}
