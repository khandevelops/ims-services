package com.usdtl.ims.master;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@Slf4j
@EnableFeignClients(
        basePackages = "com.usdtl.ims.clients"
)
public class MasterApplication {
    public static void main(String[] args) {
        SpringApplication.run(MasterApplication.class, args);
    }


}
