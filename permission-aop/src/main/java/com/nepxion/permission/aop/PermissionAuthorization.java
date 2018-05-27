package com.nepxion.permission.aop;

/**
 * <p>Title: Nepxion Permission</p>
 * <p>Description: Nepxion Permission</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nepxion.aquarius.cache.annotation.Cacheable;
import com.nepxion.permission.api.PermissionApi;
import com.nepxion.permission.constant.PermissionConstant;

@Component("permissionAuthorization")
public class PermissionAuthorization {
    private static final Logger LOG = LoggerFactory.getLogger(PermissionAuthorization.class);

    @Value("${" + PermissionConstant.PERMISSION_CACHE_INVOKE_ENABLED + ":true}")
    private Boolean cacheInvokeEnabled;

    @Autowired
    private PermissionApi permissionApi;

    // 通过自动装配的方式，自身调用自身的注解方法
    @Autowired
    private PermissionAuthorization permissionAuthorization;

    @PostConstruct
    public void initialize() {
        LOG.info("Permission cache invoke enabled is {}...", cacheInvokeEnabled);
    }

    public boolean authorize(String userId, String userType, String permissionName, String permissionType, String serviceName) {
        if (cacheInvokeEnabled) {
            // 先从分布式缓存去获取权限验证结果，如果缓存不存在，则调用后端去获取权限验证结果
            return permissionAuthorization.authorizeCache(userId, userType, permissionName, permissionType, serviceName);
        } else {
            // 每次调用后端去获取权限验证结果
            return authorizeInvoke(userId, userType, permissionName, permissionType, serviceName);
        }
    }

    public boolean authorizeInvoke(String userId, String userType, String permissionName, String permissionType, String serviceName) {
        boolean authorized = permissionApi.authorize(userId, userType, permissionName, permissionType, serviceName);

        LOG.info("Authorized={} for userId={}, userType={}, permissionName={}, permissionType={}, serviceName={}", authorized, userId, userType, permissionName, permissionType, serviceName);

        return authorized;
    }

    @Cacheable(name = "cache", key = "#userId + \"_\" + #userType + \"_\" + #permissionName + \"_\" + #permissionType + \"_\" + #serviceName", expire = -1L)
    public boolean authorizeCache(String userId, String userType, String permissionName, String permissionType, String serviceName) {
        return authorizeInvoke(userId, userType, permissionName, permissionType, serviceName);
    }
}