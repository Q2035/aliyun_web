package com.test.aliyun_web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.test.aliyun_web.mapper")
@SpringBootApplication
public class AliyunWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(AliyunWebApplication.class, args);
    }

}
