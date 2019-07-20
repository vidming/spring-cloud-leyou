package com.bj1901.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author: adming
 * @Date: 2019/6/22 0022 11:19
 */
@EnableEurekaServer
@SpringBootApplication
public class RegisterApp {

    public static void main(String[] args) {
        SpringApplication.run(RegisterApp.class, args);
    }

}
