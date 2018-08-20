package com.nepxion.permission.example.feign;

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
import org.springframework.cloud.netflix.feign.EnableFeignClients;

import com.nepxion.permission.feign.annotation.EnablePermissionFeign;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnablePermissionFeign
public class MyApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(MyApplication.class).run(args);
    }
}