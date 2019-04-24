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

import com.nepxion.banner.BannerConstant;
import com.nepxion.banner.Description;
import com.nepxion.banner.LogoBanner;
import com.nepxion.banner.NepxionBanner;
import com.nepxion.permission.aop.PermissionAuthorization;
import com.nepxion.permission.aop.PermissionAutoScanProxy;
import com.nepxion.permission.aop.PermissionInterceptor;
import com.nepxion.permission.aop.PermissionPersister;
import com.nepxion.permission.constant.PermissionConstant;
import com.nepxion.permission.context.PermissionFeignBeanFactoryPostProcessor;
import com.taobao.text.Color;

@Configuration
public class PermissionAopConfiguration {
    static {
        /*String bannerShown = System.getProperty(BannerConstant.BANNER_SHOWN, "true");
        if (Boolean.valueOf(bannerShown)) {
            System.out.println("");
            System.out.println("╔═══╗");
            System.out.println("║╔═╗║");
            System.out.println("║╚═╝╠══╦═╦╗╔╦╦══╦══╦╦══╦══╗");
            System.out.println("║╔══╣║═╣╔╣╚╝╠╣══╣══╬╣╔╗║╔╗║");
            System.out.println("║║  ║║═╣║║║║║╠══╠══║║╚╝║║║║");
            System.out.println("╚╝  ╚══╩╝╚╩╩╩╩══╩══╩╩══╩╝╚╝");
            System.out.println("Nepxion Permission  v" + PermissionConstant.PERMISSION_VERSION);
            System.out.println("");
        }*/

        LogoBanner logoBanner = new LogoBanner(PermissionAopConfiguration.class, "/com/nepxion/permission/resource/logo.txt", "Welcome to Nepxion", 10, 5, new Color[] { Color.red, Color.green, Color.cyan, Color.blue, Color.yellow, Color.magenta, Color.red, Color.green, Color.cyan, Color.blue }, true);

        NepxionBanner.show(logoBanner, new Description(BannerConstant.VERSION + ":", PermissionConstant.PERMISSION_VERSION, 0, 1), new Description(BannerConstant.GITHUB + ":", BannerConstant.NEPXION_GITHUB + "/Permission", 0, 1));
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