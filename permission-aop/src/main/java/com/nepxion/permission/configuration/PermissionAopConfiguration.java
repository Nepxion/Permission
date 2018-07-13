package com.nepxion.permission.configuration;

/**
 * <p>Title: Nepxion Permission</p>
 * <p>Description: Nepxion Permission</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nepxion.permission.aop.PermissionAuthorization;
import com.nepxion.permission.aop.PermissionAutoScanProxy;
import com.nepxion.permission.aop.PermissionInterceptor;
import com.nepxion.permission.aop.PermissionPersister;
import com.nepxion.permission.constant.PermissionConstant;
import com.nepxion.permission.context.PermissionFeignBeanFactoryPostProcessor;

@Configuration
public class PermissionAopConfiguration {
    static {
        System.out.println("");
        System.out.println("╔═══╗");
        System.out.println("║╔═╗║");
        System.out.println("║╚═╝╠══╦═╦╗╔╦╦══╦══╦╦══╦══╗");
        System.out.println("║╔══╣║═╣╔╣╚╝╠╣══╣══╬╣╔╗║╔╗║");
        System.out.println("║║  ║║═╣║║║║║╠══╠══║║╚╝║║║║");
        System.out.println("╚╝  ╚══╩╝╚╩╩╩╩══╩══╩╩══╩╝╚╝");
        System.out.println("Nepxion Permission  v1.0.29");
        System.out.println("");
    }

    @Value("${" + PermissionConstant.PERMISSION_SCAN_PACKAGES + ":}")
    private String scanPackages;

    @Bean
    public PermissionAutoScanProxy permissionAutoScanProxy() {
        return new PermissionAutoScanProxy(scanPackages);
    }

    @Bean
    public PermissionInterceptor permissionInterceptor() {
        return new PermissionInterceptor();
    }

    @Bean
    public PermissionAuthorization permissionAuthorization() {
        return new PermissionAuthorization();
    }

    @Bean
    public PermissionPersister permissionPersister() {
        return new PermissionPersister();
    }

    @Bean
    public PermissionFeignBeanFactoryPostProcessor permissionFeignBeanFactoryPostProcessor() {
        return new PermissionFeignBeanFactoryPostProcessor();
    }
}