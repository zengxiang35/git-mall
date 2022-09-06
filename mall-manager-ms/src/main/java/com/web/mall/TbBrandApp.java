package com.web.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("com.web.mall.mapper")
@EnableEurekaClient
public class TbBrandApp {
    public static void main(String[] args) {
        SpringApplication.run(TbBrandApp.class,args);
    }
}
