package com.lding.pmbok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  - @SpringBootApplication 里面包含了3个注解
 *  - @EnableAutoConfiguration 可以根据maven依赖自动构建相关环境（比如为spring-boot-starter-web构建web容器环境等）
 *  - @ComponentScan 默认会扫描当前包以及子包中的所有类
 *  - @SpringBootConfiguration 暂时无视，之后细说
 */
@SpringBootApplication
public class Application {
    static {
        // 解决druid 日志报错：discard long time none received connection:xxx
        System.setProperty("druid.mysql.usePingMethod", "false");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
