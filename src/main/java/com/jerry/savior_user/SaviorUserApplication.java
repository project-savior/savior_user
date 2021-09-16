package com.jerry.savior_user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SaviorUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(SaviorUserApplication.class, args);
    }
}
