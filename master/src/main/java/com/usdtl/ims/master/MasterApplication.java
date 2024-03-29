package com.usdtl.ims.master;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class MasterApplication {
    public static void main(String[] args) {

        SpringApplication.run(MasterApplication.class, args);
    }


}
