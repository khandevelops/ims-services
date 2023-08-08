package com.usdtl.ims.requestMaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"com.usdtl.ims"})
@EnableFeignClients(
        basePackages = "com.usdtl.ims.clients"
)
public class RequestMasterApplication {
    public static void main(String[] args) {
        SpringApplication.run(RequestMasterApplication.class, args);
    }
}