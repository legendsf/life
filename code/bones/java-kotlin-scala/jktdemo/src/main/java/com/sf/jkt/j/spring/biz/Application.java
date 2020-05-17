package com.sf.jkt.j.spring.biz;

import com.sf.jkt.j.spring.biz.classload.spi.Iprint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;


@ComponentScan(basePackages = {"com.sf.jkt.j"},excludeFilters = {
        @ComponentScan.Filter(type = FilterType.REGEX,pattern = {"com.sf.jkt.k.*"})
})
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        KafkaAutoConfiguration.class
        , RabbitAutoConfiguration.class,
        SecurityAutoConfiguration.class,
        ManagementWebSecurityAutoConfiguration.class
})
@EnableCaching
public class Application {
    public Application(Iprint printProxy) {
        printProxy.execute(10, "log print");
        printProxy.execute(0, "console print");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
