package com.test.aliyun_web.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@Configuration
public class MyMulipartConfig {

    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory =new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.ofBytes(1073741824L));
        factory.setMaxRequestSize(DataSize.ofBytes(10737418240L));
        return factory.createMultipartConfig();
    }
}
