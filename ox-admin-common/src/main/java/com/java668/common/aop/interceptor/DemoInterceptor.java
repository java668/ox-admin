package com.java668.common.aop.interceptor;

import cn.hutool.core.util.StrUtil;
import com.java668.common.aop.annotation.DemoSite;
import com.java668.common.enums.ResultEnum;
import com.java668.common.exception.BusinessException;
import com.java668.common.properties.SystemSettingProperties;
import com.java668.common.utils.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @author Jerry.chen
 * @desc 演示环境 aop拦截器
 * @date 2023/03/29 18:05
 **/
@Aspect
@RequiredArgsConstructor
public class DemoInterceptor {

    private final SystemSettingProperties systemSettingProperties;

    @Before("@annotation(demoSite)")
    public void doAfter(DemoSite demoSite) {
        if (systemSettingProperties.getIsDemoSite() && !StrUtil.equals(AuthUtils.getUsername(), "admin")) {
            throw new BusinessException(ResultEnum.DEMO_SITE_EXCEPTION);
        }
    }

}