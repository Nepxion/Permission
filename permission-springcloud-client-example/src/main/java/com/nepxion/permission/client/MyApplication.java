package com.nepxion.permission.client;

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
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Import;

import com.nepxion.permission.client.context.MyContextAware;
import com.nepxion.permission.client.service.MyService;
import com.nepxion.permission.delegate.PermissionDelegate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackageClasses = { PermissionDelegate.class })
@Import({ com.nepxion.permission.config.PermissionConfig.class })
public class MyApplication {
    private static final Logger LOG = LoggerFactory.getLogger(MyApplication.class);

    public static void main(String[] args) {
        new SpringApplicationBuilder(MyApplication.class).web(true).run(args);

        MyService myService = MyContextAware.getBean(MyService.class);
        LOG.info("Result : {}", myService.doA("zhangsan", "LDAP", "valueA"));
        LOG.info("Result : {}", myService.doB("abcd1234", "valueB"));
    }
}