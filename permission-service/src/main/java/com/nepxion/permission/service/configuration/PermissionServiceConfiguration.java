package com.nepxion.permission.service.configuration;

/**
 * <p>Title: Nepxion Permission</p>
 * <p>Description: Nepxion Permission</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nepxion.banner.BannerConstant;
import com.nepxion.banner.Description;
import com.nepxion.banner.LogoBanner;
import com.nepxion.banner.NepxionBanner;
import com.nepxion.permission.api.PermissionResource;
import com.nepxion.permission.api.UserResource;
import com.nepxion.permission.service.constant.PermissionServiceConstant;
import com.nepxion.permission.service.impl.PermissionResourceImpl;
import com.nepxion.permission.service.impl.UserResourceImpl;
import com.taobao.text.Color;

@Configuration
public class PermissionServiceConfiguration {
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
            System.out.println("Nepxion Permission  v" + PermissionServiceConstant.PERMISSION_VERSION);
            System.out.println("");
        }*/

        LogoBanner logoBanner = new LogoBanner(PermissionServiceConfiguration.class, "/com/nepxion/permission/resource/logo.txt", "Welcome to Nepxion", 10, 5, new Color[] { Color.red, Color.green, Color.cyan, Color.blue, Color.yellow, Color.magenta, Color.red, Color.green, Color.cyan, Color.blue }, true);

        NepxionBanner.show(logoBanner, new Description(BannerConstant.VERSION + ":", PermissionServiceConstant.PERMISSION_VERSION, 0, 1), new Description(BannerConstant.GITHUB + ":", BannerConstant.NEPXION_GITHUB + "/Permission", 0, 1));
    }

    @Bean
    public PermissionResource permissionResource() {
        return new PermissionResourceImpl();
    }

    @Bean
    public UserResource userResource() {
        return new UserResourceImpl();
    }
}