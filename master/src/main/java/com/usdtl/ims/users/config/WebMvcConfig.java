//package com.usdtl.ims.master.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
//
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins(
//                        "http://localhost:3000",
//                        "http://localhost:8000",
//                        "http://192.168.1.137",
//                        "http://ims.usdtl.com"
//                )
//                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE")
//                .allowCredentials(true);
//    }
//}
