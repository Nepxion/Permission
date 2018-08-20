package com.nepxion.permission.example.service;

/**
 * <p>Title: Nepxion Permission</p>
 * <p>Description: Nepxion Permission</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.nepxion.aquarius.cache.annotation.EnableCache;
import com.nepxion.permission.service.annotation.EnablePermissionSerivce;

@SpringBootApplication
@EnablePermissionSerivce
@EnableCache
@EnableDiscoveryClient
public class PermissionApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(PermissionApplication.class).run(args);
    }
}