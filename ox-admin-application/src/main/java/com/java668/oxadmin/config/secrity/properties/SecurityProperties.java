package com.java668.oxadmin.config.secrity.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Jerry.chen
 */
@Data
@Component
@ConfigurationProperties(prefix = "oxadmin.security")
public class SecurityProperties {

    private PermitProperties ignore = new PermitProperties();

}