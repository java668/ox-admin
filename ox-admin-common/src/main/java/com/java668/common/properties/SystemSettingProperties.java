package com.java668.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 系统设置
 * @author Jerry
 */
@Data
@Component
@ConfigurationProperties(prefix = "oxadmin.system")
public class SystemSettingProperties {


    /**
     * 是否是演示站点
     */
    private Boolean isDemoSite = false;

    /**
     * 是否是演示站点
     */
    private Boolean addressEnabled = true;

}
