package com.nepxion.permission.configuration;

/**
 * <p>Title: Nepxion Permission</p>
 * <p>Description: Nepxion Permission</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nepxion.permission.aop.PermissionFeignInterceptor;

@Configuration
public class PermissionFeignAutoConfiguration {
    @Bean
    public PermissionFeignInterceptor permissionFeignInterceptor() {
        return new PermissionFeignInterceptor();
    }
}