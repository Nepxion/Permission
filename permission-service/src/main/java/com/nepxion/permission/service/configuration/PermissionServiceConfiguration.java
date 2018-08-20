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

import com.nepxion.permission.api.PermissionResource;
import com.nepxion.permission.api.UserResource;
import com.nepxion.permission.service.impl.PermissionResourceImpl;
import com.nepxion.permission.service.impl.UserResourceImpl;

@Configuration
public class PermissionServiceConfiguration {
    static {
        System.out.println("");
        System.out.println("╔═══╗");
        System.out.println("║╔═╗║");
        System.out.println("║╚═╝╠══╦═╦╗╔╦╦══╦══╦╦══╦══╗");
        System.out.println("║╔══╣║═╣╔╣╚╝╠╣══╣══╬╣╔╗║╔╗║");
        System.out.println("║║  ║║═╣║║║║║╠══╠══║║╚╝║║║║");
        System.out.println("╚╝  ╚══╩╝╚╩╩╩╩══╩══╩╩══╩╝╚╝");
        System.out.println("Nepxion Permission  v2.0.5");
        System.out.println("");
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