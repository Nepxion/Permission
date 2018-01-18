package com.nepxion.permission.example;

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
import org.springframework.context.annotation.Import;

import com.nepxion.aquarius.common.context.AquariusContextAware;
import com.nepxion.permission.example.service.ExampleService;

@SpringBootApplication
@Import({ com.nepxion.permission.config.PermissionConfig.class })
public class ExampleApplication {
    private static final Logger LOG = LoggerFactory.getLogger(ExampleApplication.class);

    public static void main(String[] args) {
        new SpringApplicationBuilder(ExampleApplication.class).web(true).run(args);

        ExampleService exampleService = AquariusContextAware.getBean(ExampleService.class);
        LOG.info("Result : {}", exampleService.doA("zhangsan", "LDAP", "valueA"));
        LOG.info("Result : {}", exampleService.doB("abcd1234", "valueB"));
    }
}