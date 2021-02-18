package com.sf.config.infrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 1.必须有@Data 或者 setter
 * 2.必须配合 @ConfigurationPropertiesScan来使用（放到 BOOTAPPLICATION)
 */
@ConfigurationProperties(prefix = "demo2")
@Data
public class DataConfig {
    String msg;
}
