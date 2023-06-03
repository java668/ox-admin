package com.java668.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jerry
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "gen")
public class GeneratorProperties {

    /** 作者 */
    private String author;

    /** 生成包路径 */
    private String packageName;

    /** 自动去除表前缀，默认是false */
    private Boolean autoRemovePre;

    /** 表前缀(类名不会包含表前缀) */
    private String tablePrefix;


}
