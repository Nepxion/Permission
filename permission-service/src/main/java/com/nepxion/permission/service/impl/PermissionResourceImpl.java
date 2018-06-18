package com.nepxion.permission.service.impl;

/**
 * <p>Title: Nepxion Permission</p>
 * <p>Description: Nepxion Permission</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nepxion.permission.api.PermissionResource;
import com.nepxion.permission.entity.PermissionEntity;
import com.nepxion.permission.entity.PermissionType;
import com.nepxion.permission.service.mapper.PermissionMapper;

@RestController
public class PermissionResourceImpl implements PermissionResource {
    private static final Logger LOG = LoggerFactory.getLogger(PermissionResourceImpl.class);

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public PermissionType[] getPermissionTypes() {
        return new PermissionType[] { PermissionType.API, PermissionType.GATEWAY, PermissionType.UI };
    }

    @Override
    public PermissionEntity getPermission(@PathVariable(value = "id") Long id) {
        return permissionMapper.getPermission(id);
    }

    @Override
    public void persist(@RequestBody List<PermissionEntity> permissions) {
        for (PermissionEntity permission : permissions) {
            permission.validateName();
        }
        permissionMapper.insertUpdatePermissions(permissions);
    }

    @Override
    public boolean authorize(@PathVariable(value = "userId") String userId, @PathVariable(value = "userType") String userType, @PathVariable(value = "permissionName") String permissionName, @PathVariable(value = "permissionType") String permissionType, @PathVariable(value = "serviceName") String serviceName) {
        if (StringUtils.equals(userId, "zhangsan")) {
            return true;
        } else if (StringUtils.equals(userId, "lisi")) {
            return false;
        }

        return true;
    }
}