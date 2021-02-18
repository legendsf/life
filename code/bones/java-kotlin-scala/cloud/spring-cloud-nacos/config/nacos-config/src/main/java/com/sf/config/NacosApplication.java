package com.sf.config;

import com.sf.config.infrastructure.config.UserConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.sf")
@EnableDiscoveryClient
@ConfigurationPropertiesScan
public class NacosApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosApplication.class);
    }
}
