package com.java668.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 系统设置
 * @author Jerry
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "oxadmin.system")
public class SystemSettingProperties {


    /**
     * 是否是演示站点
     */
    private Boolean isDemoSite = false;

}
