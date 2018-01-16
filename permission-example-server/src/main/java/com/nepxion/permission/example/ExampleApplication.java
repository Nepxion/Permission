package com.nepxion.permission.example;

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
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ com.nepxion.permission.config.PermissionConfig.class })
@ComponentScan(basePackages = { "com.nepxion.permission.example" })
public class ExampleApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ExampleApplication.class).web(true).run(args);
    }
}