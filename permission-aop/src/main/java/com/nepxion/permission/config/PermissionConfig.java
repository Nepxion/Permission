package com.nepxion.permission.config;

/**
 * <p>Title: Nepxion Permission</p>
 * <p>Description: Nepxion Permission</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ com.nepxion.aquarius.cache.redis.config.RedisCacheConfig.class })
@ComponentScan(basePackages = { "com.nepxion.permission.aop" })
public class PermissionConfig {

}