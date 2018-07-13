package com.nepxion.permission.service.configuration;

/**
 * <p>Title: Nepxion Permission</p>
 * <p>Description: Nepxion Permission</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.nepxion.permission.service.impl" })
public class PermissionServiceAutoConfiguration {
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
}