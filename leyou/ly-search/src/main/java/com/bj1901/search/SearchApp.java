package com.bj1901.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: adming
 * @Date: 2019/7/8 0008 13:32
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class SearchApp {

    public static void main(String[] args) {
        SpringApplication.run(SearchApp.class, args);
    }

}
