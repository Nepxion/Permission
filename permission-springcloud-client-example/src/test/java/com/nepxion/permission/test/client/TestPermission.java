package com.nepxion.permission.test.client;

/**
 * <p>Title: Nepxion Permission</p>
 * <p>Description: Nepxion Permission</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        // getAllPermissionTypes();
        // getPermission();
        getAllPermissions();
        // getPermissions();
        // getPermissionsByServiceName();
        // getPermissionsByTypeAndServiceName();
        // getPermissionByNameAndTypeAndServiceName();
        // getPermissionsByResources();
        // getPermissionsByRoleId();
        // getPermissionsByRoleIds();
        // indicateBoundPermissions();
        // insertPermission();
        // insertUpdatePermission();
        // insertPermissions();
        // insertUpdatePermissions();
        // updatePermission();
        // deletePermission();
        // deletePermissions();
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

    public void getPermissions() {
        LOG.info("===== Permissions={}", permissionResource.getPermissions(Arrays.asList(1L, 2L, 3L)));
    }

    public void getPermissionsByServiceName() {
        LOG.info("===== Permissions={}", permissionResource.getPermissionsByServiceName("permission-springcloud-client-example"));
    }

    public void getPermissionsByTypeAndServiceName() {
        LOG.info("===== Permissions={}", permissionResource.getPermissionsByTypeAndServiceName(PermissionType.API.getValue(), "permission-springcloud-client-example"));
    }

    public void getPermissionByNameAndTypeAndServiceName() {
        LOG.info("===== Permission={}", permissionResource.getPermissionByNameAndTypeAndServiceName("A-Permission", PermissionType.API.getValue(), "permission-springcloud-client-example"));
    }

    public void getPermissionsByResources() {
        LOG.info("===== Permissions={}", permissionResource.getPermissionsByResources(Arrays.asList("http://www.iMicroService.com/1.html", "http://www.iMicroService.com/3.html")));
    }

    public void getPermissionsByRoleId() {

    }

    public void getPermissionsByRoleIds() {

    }

    public void indicateBoundPermissions() {

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

    public void insertUpdatePermission() {
        for (int i = 1; i <= 10; i++) {
            PermissionEntity permission = new PermissionEntity();
            permission.setName("My Permission" + i);
            permission.setLabel("我的权限" + i);
            permission.setType(PermissionType.UI.getValue());
            permission.setDescription("My Permission Description" + i + "-insertUpdated");
            permission.setServiceName("permission-springcloud-client-example");
            permission.setResource("http://www.iMicroService.com/" + i + ".html");
            permission.setCreateOwner("Haojun Ren");
            permission.setUpdateOwner("Haojun Ren");

            permission = permissionResource.insertUpdatePermission(permission);
            LOG.info("===== Permission={}", permission);
        }
    }

    public void insertPermissions() {
        List<PermissionEntity> permissions = new ArrayList<PermissionEntity>();
        for (int i = 11; i <= 20; i++) {
            PermissionEntity permission = new PermissionEntity();
            permission.setName("My Permission" + i);
            permission.setLabel("我的权限" + i);
            permission.setType(PermissionType.UI.getValue());
            permission.setDescription("My Permission Description" + i);
            permission.setServiceName("permission-springcloud-client-example");
            permission.setResource("http://www.iMicroService.com/" + i + ".html");
            permission.setCreateOwner("Haojun Ren");
            permission.setUpdateOwner("Haojun Ren");
            permissions.add(permission);
        }
        permissions = permissionResource.insertPermissions(permissions);
        LOG.info("===== Permissions={}", permissions);
    }

    public void insertUpdatePermissions() {
        List<PermissionEntity> permissions = new ArrayList<PermissionEntity>();
        for (int i = 11; i <= 20; i++) {
            PermissionEntity permission = new PermissionEntity();
            permission.setName("My Permission" + i);
            permission.setLabel("我的权限" + i + "-insertUpdated");
            permission.setType(PermissionType.UI.getValue());
            permission.setDescription("My Permission Description" + i);
            permission.setServiceName("permission-springcloud-client-example");
            permission.setResource("http://www.iMicroService.com/" + i + ".html");
            permission.setCreateOwner("Haojun Ren");
            permission.setUpdateOwner("Haojun Ren");
            permissions.add(permission);
        }
        permissionResource.insertUpdatePermissions(permissions);
    }

    public void updatePermission() {
        PermissionEntity permission = permissionResource.getPermission(1L);
        permission.setLabel("我的权限99-updated");
        permissionResource.updatePermission(permission);
    }

    public void deletePermission() {
        // permissionResource.deletePermission(28L);
    }

    public void deletePermissions() {
        //permissionResource.deletePermissions(Arrays.asList(29L, 30L));
    }
}