package com.usdtl.ims.storeRoomMaster;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class StoreRoomMasterApplication {
    public static void main(String[] args) {

        SpringApplication.run(StoreRoomMasterApplication.class, args);
    }
}