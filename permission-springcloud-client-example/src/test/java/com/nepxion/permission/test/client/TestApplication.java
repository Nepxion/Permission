package com.nepxion.permission.test.client;

/**
 * <p>Title: Nepxion Permission</p>
 * <p>Description: Nepxion Permission</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.nepxion.aquarius.cache.annotation.EnableCache;
import com.nepxion.permission.annotation.EnablePermission;
import com.nepxion.permission.api.PermissionResource;

@SpringBootApplication
@EnablePermission
@EnableCache
public class TestApplication {
    private static final Logger LOG = LoggerFactory.getLogger(TestApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(TestApplication.class, args);

        PermissionResource permissionResource = applicationContext.getBean(PermissionResource.class);

        LOG.info("===== PermissionTypes : {}", Arrays.asList(permissionResource.getPermissionTypes()));

        LOG.info("===== Permission: {}", permissionResource.getPermission(1L));
    }
}