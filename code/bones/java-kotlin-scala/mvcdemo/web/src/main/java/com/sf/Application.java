package com.sf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/***
 * log4j2 异步日志 有坑，所以用 logback 最稳妥最简单
 * https://www.cnblogs.com/yangfeiORfeiyang/p/9775863.html
 *
 * https://blog.csdn.net/seanxwq/article/details/103977487
 *
 * https://blog.csdn.net/u011493218/article/details/86607172
 * ***************************
 * spring cache
 * https://blog.csdn.net/qq_32448349/article/details/101696892
 */
@SpringBootApplication
@EnableCaching
public class Application {
    public static void main(String[] args) {
        //下面语句使得日志输出使用异步处理，减小输出日志对性能的影响
//        System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
        SpringApplication.run(Application.class);
    }
}
