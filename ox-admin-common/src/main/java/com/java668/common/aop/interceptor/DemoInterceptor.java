package com.java668.common.aop.interceptor;

import cn.hutool.core.util.StrUtil;
import com.java668.common.aop.annotation.DemoSite;
import com.java668.common.enums.ResultCodeEnum;
import com.java668.common.exception.BusinessException;
import com.java668.common.properties.SystemSettingProperties;
import com.java668.common.utils.AuthUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author Jerry.chen
 * @desc 演示环境 aop拦截器
 * @date 2023/03/29 18:05
 **/
@Aspect
@EnableConfigurationProperties({SystemSettingProperties.class})
public class DemoInterceptor {

    @Autowired
    private SystemSettingProperties systemSettingProperties;

    @Before("@annotation(demoSite)")
    public void doAfter(DemoSite demoSite) {
        if (systemSettingProperties.getIsDemoSite() && !StrUtil.equals(AuthUtil.getUsername(), "admin")) {
            throw new BusinessException(ResultCodeEnum.DEMO_SITE_EXCEPTION);
        }
    }

}