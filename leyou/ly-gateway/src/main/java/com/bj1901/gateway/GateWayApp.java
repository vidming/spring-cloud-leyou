package com.bj1901.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Author: adming
 * @Date: 2019/6/22 0022 11:33
 */
@EnableZuulProxy
@SpringBootApplication
public class GateWayApp {

    public static void main(String[] args) {
        SpringApplication.run(GateWayApp.class, args);
    }

}
