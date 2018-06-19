package com.nepxion.permission.service.impl;

/**
 * <p>Title: Nepxion Permission</p>
 * <p>Description: Nepxion Permission</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public PermissionType[] getAllPermissionTypes() {
        return new PermissionType[] { PermissionType.API, PermissionType.GATEWAY, PermissionType.UI };
    }

    @Override
    public PermissionEntity getPermission(@PathVariable(value = "id") Long id) {
        return permissionMapper.getPermission(id);
    }

    @Override
    public List<PermissionEntity> getAllPermissions() {
        return permissionMapper.getAllPermissions();
    }

    @Override
    public List<PermissionEntity> getPermissions(@RequestBody List<Long> ids) {
        return permissionMapper.getPermissions(ids);
    }

    @Override
    public List<PermissionEntity> getPermissionsByServiceName(@PathVariable(value = "serviceName") String serviceName) {
        return permissionMapper.getPermissionsByServiceName(serviceName);
    }

    @Override
    public List<PermissionEntity> getPermissionsByTypeAndServiceName(@PathVariable(value = "type") String type, @PathVariable(value = "serviceName") String serviceName) {
        return permissionMapper.getPermissionsByTypeAndServiceName(type, serviceName);
    }

    @Override
    public PermissionEntity getPermissionByNameAndTypeAndServiceName(@PathVariable(value = "name") String name, @PathVariable(value = "type") String type, @PathVariable(value = "serviceName") String serviceName) {
        return permissionMapper.getPermissionByNameAndTypeAndServiceName(name, type, serviceName);
    }

    @Override
    public List<PermissionEntity> getPermissionsByResources(@RequestBody List<String> resources) {
        return permissionMapper.getPermissionsByResources(resources);
    }

    @Override
    public List<PermissionEntity> getPermissionsByRoleId(@PathVariable(value = "roleId") Long roleId) {
        return permissionMapper.getPermissionsByRoleId(roleId);
    }

    @Override
    public List<PermissionEntity> getPermissionsByRoleIds(@RequestBody List<Long> roleIds) {
        return permissionMapper.getPermissionsByRoleIds(roleIds);
    }

    @Override
    public List<PermissionEntity> indicateBoundPermissions(@RequestBody List<PermissionEntity> permissions) {
        //        for (PermissionEntity permission : permissions) {
        //            Long permissionId = permission.getId();
        //            List<Long> roleIds = rolePermissionMapper.queryRoleIdsByPermissionId(permissionId);
        //            if (CollectionUtils.isNotEmpty(roleIds)) {
        //                permission.setBoundStatus(BoundStatus.YES.getValue());
        //            } else {
        //                permission.setBoundStatus(BoundStatus.NO.getValue());
        //            }
        //        }

        return permissions;
    }

    @Override
    public PermissionEntity insertPermission(@RequestBody PermissionEntity permission) {
        permission.validateName();

        permissionMapper.insertPermission(permission);

        Long id = permission.getId();

        return permissionMapper.getPermission(id);
    }

    @Override
    public PermissionEntity insertUpdatePermission(@RequestBody PermissionEntity permission) {
        permission.validateName();

        permissionMapper.insertUpdatePermission(permission);

        Long id = permission.getId();

        return permissionMapper.getPermission(id);
    }

    @Override
    public List<PermissionEntity> insertPermissions(@RequestBody List<PermissionEntity> permissions) {
        for (PermissionEntity permission : permissions) {
            permission.validateName();
        }

        permissionMapper.insertPermissions(permissions);

        List<Long> ids = new ArrayList<Long>();
        for (PermissionEntity permission : permissions) {
            Long id = permission.getId();
            ids.add(id);
        }

        return permissionMapper.getPermissions(ids);
    }

    @Override
    public void insertUpdatePermissions(@RequestBody List<PermissionEntity> permissions) {
        for (PermissionEntity permission : permissions) {
            permission.validateName();
        }

        permissionMapper.insertUpdatePermissions(permissions);
    }

    @Override
    public void updatePermission(@RequestBody PermissionEntity permission) {
        permission.validateName();

        permissionMapper.updatePermission(permission);
    }

    @Override
    public void deletePermission(@PathVariable(value = "id") Long id) {
        //        if (rolePermissionMapper.containsPermission(id) != 0) {
        //            throw new ServiceException("Delete permission error by id=" + id + ", it has been referenced by a role");
        //        }
        //
        //        permissionMapper.deletePermission(id);
    }

    @Override
    public void deletePermissions(@RequestBody List<Long> ids) {
        //        if (CollectionUtils.isNotEmpty(ids)) {
        //            permissionMapper.deletePermissions(ids);
        //        }
    }

    @Override
    public void persist(@RequestBody List<PermissionEntity> permissions) {
        for (PermissionEntity permission : permissions) {
            permission.validateName();
        }

        permissionMapper.insertUpdatePermissions(permissions);
    }
}