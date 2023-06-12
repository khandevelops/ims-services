package com.usdtl.ims.departmentMaster.master;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class ProfileDetailsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProfileDetailsApplication.class, args);
    }


}
