package com.usdtl.ims.download.department;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(
        basePackages = "com.usdtl.ims.clients"
)
public class DepartmentDownloadApplication {
    public static void main(String[] args) {
        SpringApplication.run(DepartmentDownloadApplication.class, args);
    }
}