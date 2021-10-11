package com.jerry.savior_user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 22454
 */
@EnableDiscoveryClient
@MapperScan(basePackages = "com.jerry.savior_user.mybatis.mapper")
@SpringBootApplication(scanBasePackages = "com.jerry")
public class SaviorUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(SaviorUserApplication.class, args);
    }
}
