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

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nepxion.permission.api.AuthorizationResource;
import com.nepxion.permission.entity.PermissionEntity;
import com.nepxion.permission.entity.RoleEntity;
import com.nepxion.permission.entity.RolePermissionEntity;
import com.nepxion.permission.entity.UserEntity;
import com.nepxion.permission.entity.UserRoleEntity;
import com.nepxion.permission.service.exception.PermissionServiceException;
import com.nepxion.permission.service.mapper.PermissionMapper;
import com.nepxion.permission.service.mapper.RoleMapper;
import com.nepxion.permission.service.mapper.RolePermissionMapper;
import com.nepxion.permission.service.mapper.UserRoleMapper;

@RestController
public class AuthorizationResourceImpl implements AuthorizationResource {
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public RolePermissionEntity getRolePermission(@PathVariable("id") Long id) {
        return rolePermissionMapper.getRolePermission(id);
    }

    @Override
    public List<RolePermissionEntity> getAllRolePermissions() {
        return rolePermissionMapper.getAllRolePermissions();
    }

    @Override
    public List<RolePermissionEntity> getRolePermissions(@RequestBody List<Long> ids) {
        return rolePermissionMapper.getRolePermissions(ids);
    }

    @Override
    public List<RolePermissionEntity> getRolePermissionsByServiceName(@PathVariable("serviceName") String serviceName) {
        return rolePermissionMapper.getRolePermissionsByServiceName(serviceName);
    }

    @Override
    public List<RolePermissionEntity> getRolePermissionsByRoleIdAndServiceName(@PathVariable("roleId") Long roleId, @PathVariable("serviceName") String serviceName) {
        return rolePermissionMapper.getRolePermissionsByRoleIdAndServiceName(roleId, serviceName);
    }

    @Override
    public List<Long> getPermissionIdsByRoleId(@PathVariable("roleId") Long roleId) {
        return rolePermissionMapper.getPermissionIdsByRoleId(roleId);
    }

    @Override
    public List<Long> getRoleIdsByPermissionId(@PathVariable("permissionId") Long permissionId) {
        return rolePermissionMapper.getRoleIdsByPermissionId(permissionId);
    }

    @Override
    public RolePermissionEntity bindRolePermission(@RequestBody RolePermissionEntity rolePermission) {
        validateRolePermission(rolePermission);

        rolePermissionMapper.insertRolePermission(rolePermission);

        Long id = rolePermission.getId();

        return rolePermissionMapper.getRolePermission(id);
    }

    @Override
    public void bindUpdateRolePermission(@RequestBody RolePermissionEntity rolePermission) {
        validateRolePermission(rolePermission);

        rolePermissionMapper.insertUpdateRolePermission(rolePermission);
    }

    private void validateRolePermission(RolePermissionEntity rolePermission) {
        Long permissionId = rolePermission.getPermissionId();
        PermissionEntity permission = permissionMapper.getPermission(permissionId);
        if (permission == null) {
            throw new PermissionServiceException("Permission isn't existed, id=" + permissionId);
        }

        Long roleId = rolePermission.getRoleId();
        RoleEntity role = roleMapper.getRole(roleId);
        if (role == null) {
            throw new PermissionServiceException("Role isn't existed, id=" + roleId);
        }
    }

    @Override
    public List<RolePermissionEntity> bindRolePermissions(@RequestBody List<RolePermissionEntity> rolePermissions) {
        validateRolePermissions(rolePermissions);

        rolePermissionMapper.insertRolePermissions(rolePermissions);

        List<Long> ids = new ArrayList<Long>();
        for (RolePermissionEntity rolePermission : rolePermissions) {
            Long id = rolePermission.getId();
            ids.add(id);
        }

        return rolePermissionMapper.getRolePermissions(ids);
    }

    @Override
    public void bindUpdateRolePermissions(@RequestBody List<RolePermissionEntity> rolePermissions) {
        validateRolePermissions(rolePermissions);

        rolePermissionMapper.insertUpdateRolePermissions(rolePermissions);
    }

    private void validateRolePermissions(List<RolePermissionEntity> rolePermissions) {
        List<Long> permissionIds = new ArrayList<Long>();
        List<Long> roleIds = new ArrayList<Long>();
        for (RolePermissionEntity rolePermission : rolePermissions) {
            Long permissionId = rolePermission.getPermissionId();
            permissionIds.add(permissionId);

            Long roleId = rolePermission.getRoleId();
            roleIds.add(roleId);
        }

        List<PermissionEntity> permissions = permissionMapper.getPermissions(permissionIds);
        if (permissions.size() != permissionIds.size()) {
            throw new PermissionServiceException("Some permissions aren't existed");
        }

        List<RoleEntity> roles = roleMapper.getRoles(roleIds);
        if (roles.size() != roleIds.size()) {
            throw new PermissionServiceException("Some roles aren't existed");
        }
    }

    @Override
    public void unbindRolePermission(@PathVariable("id") Long id) {
        rolePermissionMapper.deleteRolePermission(id);
    }

    @Override
    public void unbindRolePermission(@PathVariable("roleId") Long roleId, @PathVariable("permissionId") Long permissionId) {
        rolePermissionMapper.deleteRolePermissionByRoleIdAndPermissionId(roleId, permissionId);
    }

    @Override
    public void unbindRolePermissions(@RequestBody List<RolePermissionEntity> rolePermissions) {
        rolePermissionMapper.deleteRolePermissions(rolePermissions);
    }

    @Override
    public void unbindRolePermissionsByRoleId(@PathVariable("roleId") Long roleId) {
        rolePermissionMapper.deleteRolePermissionByRoleId(roleId);
    }

    @Override
    public void updateRolePermission(@RequestBody RolePermissionEntity rolePermission) {
        rolePermissionMapper.updateRolePermission(rolePermission);
    }

    @Override
    public UserRoleEntity getUserRole(@PathVariable("id") Long id) {
        return userRoleMapper.getUserRole(id);
    }

    @Override
    public List<UserRoleEntity> getAllUserRoles() {
        return userRoleMapper.getAllUserRoles();
    }

    @Override
    public List<UserRoleEntity> getUserRoles(@RequestBody List<Long> ids) {
        return userRoleMapper.getUserRoles(ids);
    }

    @Override
    public List<UserRoleEntity> getUserRolesByServiceName(@PathVariable("serviceName") String serviceName) {
        return userRoleMapper.getUserRolesByServiceName(serviceName);
    }

    @Override
    public List<UserRoleEntity> getUserRolesByUserIdAndUserTypeAndServiceName(@PathVariable("userId") String userId, @PathVariable("userType") String userType, @PathVariable("serviceName") String serviceName) {
        return userRoleMapper.getUserRolesByUserIdAndUserTypeAndServiceName(userId, userType, serviceName);
    }

    @Override
    public List<Long> getRoleIdsByUserIdAndUserType(@PathVariable("userId") String userId, @PathVariable("userType") String userType) {
        return userRoleMapper.getRoleIdsByUserIdAndUserType(userId, userType);
    }

    @Override
    public List<UserEntity> getUsersByRoleId(@PathVariable("roleId") Long roleId) {
        return userRoleMapper.getUsersByRoleId(roleId);
    }

    @Override
    public List<UserRoleEntity> getUserRolesByPermissonId(@PathVariable("permissonId") Long permissionId) {
        return userRoleMapper.getUserRolesByPermissonId(permissionId);
    }

    @Override
    public UserRoleEntity bindUserRole(@RequestBody UserRoleEntity userRole) {
        validateUserRole(userRole);

        userRoleMapper.insertUserRole(userRole);

        Long id = userRole.getId();

        return userRoleMapper.getUserRole(id);
    }

    @Override
    public void bindUpdateUserRole(@RequestBody UserRoleEntity userRole) {
        validateUserRole(userRole);

        userRoleMapper.insertUpdateUserRole(userRole);
    }

    private void validateUserRole(UserRoleEntity userRole) {
        Long roleId = userRole.getRoleId();
        RoleEntity role = roleMapper.getRole(roleId);
        if (role == null) {
            throw new PermissionServiceException("Role isn't existed, id=" + roleId);
        }
    }

    @Override
    public List<UserRoleEntity> bindUserRoles(@RequestBody List<UserRoleEntity> userRoles) {
        validateUserRoles(userRoles);

        userRoleMapper.insertUserRoles(userRoles);

        List<Long> ids = new ArrayList<Long>();
        for (UserRoleEntity userRole : userRoles) {
            Long id = userRole.getId();
            ids.add(id);
        }

        return userRoleMapper.getUserRoles(ids);
    }

    @Override
    public void bindUpdateUserRoles(@RequestBody List<UserRoleEntity> userRoles) {
        validateUserRoles(userRoles);

        userRoleMapper.insertUpdateUserRoles(userRoles);
    }

    private void validateUserRoles(List<UserRoleEntity> userRoles) {
        List<Long> roleIds = new ArrayList<Long>();
        for (UserRoleEntity userRole : userRoles) {
            Long roleId = userRole.getRoleId();
            roleIds.add(roleId);
        }

        List<RoleEntity> roles = roleMapper.getRoles(roleIds);
        if (roles.size() != roleIds.size()) {
            throw new PermissionServiceException("Some roles aren't existed");
        }
    }

    @Override
    public void unbindUserRole(@PathVariable("id") Long id) {
        userRoleMapper.deleteUserRole(id);
    }

    @Override
    public void unbindUserRole(@PathVariable("userId") String userId, @PathVariable("userType") String userType, @PathVariable("roleId") Long roleId) {
        userRoleMapper.deleteUserRoleByUserIdAndUserTypeAndRoleId(userId, userType, roleId);
    }

    @Override
    public void unbindUserRoles(@RequestBody List<UserRoleEntity> userRoles) {
        userRoleMapper.deleteUserRoles(userRoles);
    }

    @Override
    public void unbindUserRolesByUserIdAndUserType(@PathVariable("userId") String userId, @PathVariable("userType") String userType) {
        userRoleMapper.deleteUserRolesByUserIdAndUserType(userId, userType);
    }

    @Override
    public void updateUserRole(@RequestBody UserRoleEntity userRole) {
        userRoleMapper.updateUserRole(userRole);
    }

    @Override
    public boolean authorize(@PathVariable(value = "userId") String userId, @PathVariable(value = "userType") String userType, @PathVariable(value = "permissionName") String permissionName, @PathVariable(value = "permissionType") String permissionType, @PathVariable(value = "serviceName") String serviceName) {
        List<Long> roleIds = permissionMapper.authorize(userId, userType, permissionName, permissionType, serviceName);

        return CollectionUtils.isNotEmpty(roleIds);
    }
}