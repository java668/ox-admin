package com.java668.common.db.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Jerry.chen
 * @desc MybatisPlusAutoFillProperties
 * @date 2023/03/29 18:05
 **/
@Setter
@Getter
@ConfigurationProperties(prefix = "java668.record-platform.mybatis-plus.auto-fill")
public class MybatisPlusAutoFillProperties {
    /**
     * 是否开启自动填充字段
     */
    private Boolean enabled = true;
    /**
     * 是否开启了插入填充
     */
    private Boolean enableInsertFill = true;
    /**
     * 是否开启了更新填充
     */
    private Boolean enableUpdateFill = true;

    /**
     * 创建人字段名
     */
    private String createByField = "createBy";

    /**
     * 更新人字段名
     */
    private String updateByField = "updateBy";

    /**
     * 创建时间字段名
     */
    private String createTimeField = "createTime";

    /**
     * 更新时间字段名
     */
    private String updateTimeField = "updateTime";
}
