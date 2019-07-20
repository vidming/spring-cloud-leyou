package com.bj1901.item;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: adming
 * @Date: 2019/6/22 0022 11:57
 * // @EnableDiscoveryClient
 */
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.bj1901.leyou","com.bj1901.item"})
@MapperScan("com.bj1901.item.mapper")
public class ItemServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(ItemServiceApp.class, args);
    }

}
