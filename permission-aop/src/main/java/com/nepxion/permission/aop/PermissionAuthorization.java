package com.nepxion.permission.aop;

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
import org.springframework.beans.factory.annotation.Autowired;

import com.nepxion.aquarius.cache.annotation.Cacheable;
import com.nepxion.permission.api.PermissionResource;

public class PermissionAuthorization {
    private static final Logger LOG = LoggerFactory.getLogger(PermissionAuthorization.class);

    @Autowired
    private PermissionResource permissionResource;

    // 通过自动装配的方式，自身调用自身的注解方法
    @Autowired
    private PermissionAuthorization permissionAuthorization;

    public boolean authorize(String userId, String userType, String permissionName, String permissionType, String serviceName) {
        return permissionAuthorization.authorizeCache(userId, userType, permissionName, permissionType, serviceName);
    }

    @Cacheable(name = "cache", key = "#userId + \"_\" + #userType + \"_\" + #permissionName + \"_\" + #permissionType + \"_\" + #serviceName", expire = -1L)
    public boolean authorizeCache(String userId, String userType, String permissionName, String permissionType, String serviceName) {
        boolean authorized = permissionResource.authorize(userId, userType, permissionName, permissionType, serviceName);

        LOG.info("Authorized={} for userId={}, userType={}, permissionName={}, permissionType={}, serviceName={}", authorized, userId, userType, permissionName, permissionType, serviceName);

        return authorized;
    }
}