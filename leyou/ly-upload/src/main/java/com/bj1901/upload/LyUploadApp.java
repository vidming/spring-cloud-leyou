package com.bj1901.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: adming
 * @Date: 2019/6/25 0025 18:48
 */
@SpringBootApplication
@EnableDiscoveryClient
public class LyUploadApp {

    public static void main(String[] args) {
        SpringApplication.run(LyUploadApp.class, args);
    }

}
