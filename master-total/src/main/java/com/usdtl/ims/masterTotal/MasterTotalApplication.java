package com.usdtl.ims.masterTotal;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MasterTotalApplication {
    public static void main(String[] args) {
        SpringApplication.run(MasterTotalApplication.class, args);
    }
}
