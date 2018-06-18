package com.nepxion.permission.test.client;

/**
 * <p>Title: Nepxion Permission</p>
 * <p>Description: Nepxion Permission</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nepxion.permission.api.PermissionResource;
import com.nepxion.permission.entity.PermissionEntity;
import com.nepxion.permission.entity.PermissionType;

@Component
public class TestPermission {
    private static final Logger LOG = LoggerFactory.getLogger(TestPermission.class);

    @Autowired
    private PermissionResource permissionResource;

    public void test() {
        getAllPermissionTypes();
        // getPermission();
        // getAllPermissions();
        // insertPermission();
    }

    public void getAllPermissionTypes() {
        LOG.info("===== PermissionTypes={}", Arrays.asList(permissionResource.getAllPermissionTypes()));
    }

    public void getPermission() {
        LOG.info("===== Permission={}", permissionResource.getPermission(1L));
    }

    public void getAllPermissions() {
        LOG.info("===== Permissions={}", permissionResource.getAllPermissions());
    }

    public void insertPermission() {
        for (int i = 1; i <= 10; i++) {
            PermissionEntity permission = new PermissionEntity();
            permission.setName("My Permission" + i);
            permission.setLabel("我的权限" + i);
            permission.setType(PermissionType.UI.getValue());
            permission.setDescription("My Permission Description" + i);
            permission.setServiceName("permission-springcloud-client-example");
            permission.setResource("http://www.iMicroService.com/" + i + ".html");
            permission.setCreateOwner("Haojun Ren");
            permission.setUpdateOwner("Haojun Ren");

            permission = permissionResource.insertPermission(permission);
            LOG.info("===== Permission={}", permission);
        }
    }
}