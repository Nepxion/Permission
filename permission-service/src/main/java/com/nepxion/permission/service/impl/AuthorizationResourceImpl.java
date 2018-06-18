package com.nepxion.permission.service.impl;

/**
 * <p>Title: Nepxion Permission</p>
 * <p>Description: Nepxion Permission</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nepxion.permission.api.AuthorizationResource;
import com.nepxion.permission.service.mapper.PermissionMapper;

@RestController
public class AuthorizationResourceImpl implements AuthorizationResource {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public boolean authorize(@PathVariable(value = "userId") String userId, @PathVariable(value = "userType") String userType, @PathVariable(value = "permissionName") String permissionName, @PathVariable(value = "permissionType") String permissionType, @PathVariable(value = "serviceName") String serviceName) {
        List<Long> roleIds = permissionMapper.authorize(userId, userType, permissionName, permissionType, serviceName);

        return CollectionUtils.isNotEmpty(roleIds);
    }
}