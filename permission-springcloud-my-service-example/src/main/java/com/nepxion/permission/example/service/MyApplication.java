package com.nepxion.permission.example.service;

/**
 * <p>Title: Nepxion Permission</p>
 * <p>Description: Nepxion Permission</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

import com.nepxion.aquarius.cache.annotation.EnableCache;
import com.nepxion.permission.annotation.EnablePermission;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = { "com.nepxion.permission.api" })
@EnablePermission
@EnableCache
public class MyApplication {
    private static final Logger LOG = LoggerFactory.getLogger(MyApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(MyApplication.class, args);

        MyController myController = applicationContext.getBean(MyController.class);
        try {
            LOG.info("Result : {}", myController.doA("zhangsan", "LDAP", "valueA"));
        } catch (Exception e) {
            LOG.error("Error", e);
        }

        try {
            LOG.info("Result : {}", myController.doB("abcd1234", "valueB"));
        } catch (Exception e) {
            LOG.error("Error", e);
        }
    }
}