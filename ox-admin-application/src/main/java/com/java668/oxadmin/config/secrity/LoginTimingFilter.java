package com.java668.oxadmin.config.secrity;

import com.java668.common.constant.Constants;
import org.springframework.util.StopWatch;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Jerry.chen
 */
public class LoginTimingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        // 在登录请求之前记录开始时间 在退出请求之前记录开始时间
        if (requestURI.equals("/login") || requestURI.equals("/logout")) {
            request.setAttribute(Constants.LOG_START_TIME_ATTRIBUTE, System.currentTimeMillis());
        }
        chain.doFilter(request, response);
    }

}
