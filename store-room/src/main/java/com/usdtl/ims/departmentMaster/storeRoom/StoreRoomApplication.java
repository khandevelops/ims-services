package com.usdtl.ims.departmentMaster.storeRoom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class StoreRoomApplication {
    public static void main(String[] args) {

        SpringApplication.run(StoreRoomApplication.class, args);
    }
}