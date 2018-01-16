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
import org.springframework.stereotype.Component;

import com.nepxion.aquarius.cache.annotation.Cacheable;

@Component("permissionAuthorization")
public class PermissionAuthorization {
    private static final Logger LOG = LoggerFactory.getLogger(PermissionAuthorization.class);

    @Cacheable(name = "permission_cache", key = "#userId + \"-\" + #userType + \"-\" + #permissionName + \"-\" + #permissionType + \"-\" + #serviceName", expire = -1L)
    public Boolean authorize(String userId, String userType, String permissionName, String permissionType, String serviceName) {
        // Boolean authorized = authorizationResource.authorizeByUserId(userId, userType, permissionName, permissionType, serviceName);
        Boolean authorized = true;

        LOG.debug("Authorized={} for userId={}, userType={}, permissionName={}, permissionType={}, serviceName={}", authorized, userId, userType, permissionName, permissionType, serviceName);

        return authorized;
    }
}