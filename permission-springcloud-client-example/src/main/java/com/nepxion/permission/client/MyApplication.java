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
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.nepxion.permission.annotation.EnablePermission;
import com.nepxion.permission.client.service.MyService;

@SpringBootApplication
@EnablePermission
public class MyApplication {
    private static final Logger LOG = LoggerFactory.getLogger(MyApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(MyApplication.class, args);

        MyService myService = applicationContext.getBean(MyService.class);
        LOG.info("Result : {}", myService.doA("zhangsan", "LDAP", "valueA"));
        LOG.info("Result : {}", myService.doB("abcd1234", "valueB"));
    }
}