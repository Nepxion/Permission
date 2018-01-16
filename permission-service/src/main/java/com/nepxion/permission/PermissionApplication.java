package com.nepxion.permission;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ com.nepxion.skeleton.framework.config.SkeletonFrameworkConfig.class })
public class PermissionApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(PermissionApplication.class).web(true).run(args);
    }
}