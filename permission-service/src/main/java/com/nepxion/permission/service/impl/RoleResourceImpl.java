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

import com.nepxion.permission.api.RoleResource;
import com.nepxion.permission.entity.RoleEntity;
import com.nepxion.permission.service.mapper.RoleMapper;

@RestController
public class RoleResourceImpl implements RoleResource {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public RoleEntity getRole(@PathVariable(value = "id") Long id) {
        return roleMapper.getRole(id);
    }

    @Override
    public List<RoleEntity> getAllRoles() {
        return roleMapper.getAllRoles();
    }

    @Override
    public List<RoleEntity> getRoles(@RequestBody List<Long> ids) {
        return roleMapper.getRoles(ids);
    }

    @Override
    public List<RoleEntity> getRolesByServiceName(@PathVariable(value = "serviceName") String serviceName) {
        return roleMapper.getRolesByServiceName(serviceName);
    }

    @Override
    public RoleEntity getRoleByNameAndServiceName(@PathVariable(value = "name") String name, @PathVariable(value = "serviceName") String serviceName) {
        return roleMapper.getRoleByNameAndServiceName(name, serviceName);
    }

    @Override
    public List<RoleEntity> getRolesByPermissionId(@PathVariable(value = "permissionId") Long permissionId) {
        return roleMapper.getRolesByPermissionId(permissionId);
    }

    @Override
    public List<RoleEntity> getRolesByPermissionIds(@RequestBody List<Long> permissionIds) {
        return roleMapper.getRolesByPermissionIds(permissionIds);
    }

    @Override
    public List<RoleEntity> getRolesByUserIdAndUserType(@PathVariable(value = "userId") String userId, @PathVariable(value = "userType") String userType) {
        return roleMapper.getRolesByUserIdAndUserType(userId, userType);
    }

    @Override
    public List<RoleEntity> indicateBoundRoles(@RequestBody List<RoleEntity> roles) {
        //        for (RoleEntity role : roles) {
        //            Long roleId = role.getId();
        //            List<UserEntity> users = userRoleMapper.queryUsersByRoleId(roleId);
        //            List<Long> permissionIds = rolePermissionMapper.queryPermissionIdsByRoleId(roleId);
        //            if (CollectionUtils.isNotEmpty(users) || CollectionUtils.isNotEmpty(permissionIds)) {
        //                role.setBoundStatus(BoundStatus.YES.getValue());
        //            } else {
        //                role.setBoundStatus(BoundStatus.NO.getValue());
        //            }
        //        }

        return roles;
    }

    @Override
    public RoleEntity insertRole(@RequestBody RoleEntity role) {
        roleMapper.insertRole(role);

        Long id = role.getId();

        return roleMapper.getRole(id);
    }

    @Override
    public RoleEntity insertUpdateRole(@RequestBody RoleEntity role) {
        roleMapper.insertUpdateRole(role);

        Long id = role.getId();

        return roleMapper.getRole(id);
    }

    @Override
    public List<RoleEntity> insertRoles(@RequestBody List<RoleEntity> roles) {
        roleMapper.insertRoles(roles);

        List<Long> ids = new ArrayList<Long>();
        for (RoleEntity role : roles) {
            Long id = role.getId();
            ids.add(id);
        }

        return roleMapper.getRoles(ids);
    }

    @Override
    public void insertUpdateRoles(@RequestBody List<RoleEntity> roles) {
        roleMapper.insertUpdateRoles(roles);
    }

    @Override
    public void updateRole(@RequestBody RoleEntity role) {
        roleMapper.updateRole(role);
    }

    @Override
    public void deleteRole(@PathVariable(value = "id") Long id) {
        //        if (rolePermissionMapper.containsRole(id) != 0) {
        //            throw new ServiceException("Delete role error by id=" + id + ", it has been referenced by a permission");
        //        }
        //
        //        if (userRoleMapper.containsRole(id) != 0) {
        //            throw new ServiceException("Delete role error by id=" + id + ", it has been referenced by a user");
        //        }
        //
        //        roleMapper.deleteRole(id);
    }

    @Override
    public void deleteRoles(@RequestBody List<Long> ids) {
        //        if (CollectionUtils.isNotEmpty(ids)) {
        //            roleMapper.deleteRoles(ids);
        //        }
    }
}