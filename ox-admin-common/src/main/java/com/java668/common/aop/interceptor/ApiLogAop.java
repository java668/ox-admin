package com.java668.common.aop.interceptor;

import cn.hutool.core.map.MapUtil;
import cn.hutool.http.Header;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.mzt.logapi.context.LogRecordContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

/**
 * 功能描述 @Author sean @Description 使用Aop拦截Controller层需要进行日志记录的API @Date 6:09 下午 2020/11/30
 */
@Slf4j
@Aspect
@Order
public class ApiLogAop {

    /**
     * 记录module下所有rest子包的controller
     */
    @Pointcut("execution(public * com.java668.oxadmin.modules.*.controller.*.*(..))"
            + "||execution(public * com.java668.oxadmin.*.modules.*.*(..))")
    public void webLog() {
    }

    /**
     * 在方法执行前执行
     *
     * @param joinPoint 连接点
     */
    @Before(value = "webLog()")
    private void doBefore(JoinPoint joinPoint) {
        //判空，如果为空则返回
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return;
        }
        HttpServletRequest request = (requestAttributes).getRequest();
        log.info("========================= Request Start =========================");
        log.info("url : " + request.getRequestURL().toString());
        log.info("http_method: " + request.getMethod());

        /**
         * add 客户端信息
         */
        UserAgent ua = UserAgentUtil.parse(request.getHeader(Header.USER_AGENT.toString()));
        log.info("Platform: {}", ua.getPlatform().toString());
        log.info("客户端信息: {}", JSONUtil.toJsonStr(ua));

        log.info("IP : " + request.getRemoteAddr());
        log.info(
                "CLASS_METHOD : "
                        + joinPoint.getSignature().getDeclaringTypeName()
                        + "."
                        + joinPoint.getSignature().getName());
        String contentType = request.getContentType();
        if (contentType != null && contentType.contains(MimeTypeUtils.APPLICATION_JSON_VALUE)) {
            Object[] args = joinPoint.getArgs();
            if (args.length > 0) {
                log.info("args : {}", JSONObject.toJSONString(args[0]));
            }
        } else {
            log.info("args : {}", Arrays.toString(joinPoint.getArgs()));
        }
        Map<String, String> requestInfo = Maps.newHashMap();
        requestInfo.put("ip", request.getRemoteAddr());
        requestInfo.put("args", "request.getRemoteAddr()");
        requestInfo.put("contentType", contentType);
        LogRecordContext.putGlobalVariable("requestInfo", requestInfo);
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        log.info("resp: " + JSONObject.toJSONString(ret));
        LogRecordContext.clear();
        log.info("========================= Request End =========================");
    }

}
