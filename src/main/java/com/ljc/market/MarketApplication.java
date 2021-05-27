package com.ljc.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@ServletComponentScan//使用session监听器需要加此注解
public class MarketApplication {
    public static void main(String[] args) {
        SpringApplication.run(MarketApplication.class,args);
    }
}