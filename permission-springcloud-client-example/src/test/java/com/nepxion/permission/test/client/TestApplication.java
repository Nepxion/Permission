package com.nepxion.permission.test.client;

/**
 * <p>Title: Nepxion Permission</p>
 * <p>Description: Nepxion Permission</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.nepxion.aquarius.cache.annotation.EnableCache;
import com.nepxion.permission.annotation.EnablePermission;

@SpringBootApplication
@EnablePermission
@EnableCache
public class TestApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(TestApplication.class, args);

        TestPermission testPermission = applicationContext.getBean(TestPermission.class);

        testPermission.test();
    }
}