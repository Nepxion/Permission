package com.nepxion.permission.service.mapper;

/**
 * <p>Title: Nepxion Permission</p>
 * <p>Description: Nepxion Permission</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nepxion.permission.entity.RolePermissionEntity;

public interface RolePermissionMapper {
    RolePermissionEntity getRolePermission(Long id);

    List<RolePermissionEntity> getAllRolePermissions();

    List<RolePermissionEntity> getRolePermissions(List<Long> ids);

    List<RolePermissionEntity> getRolePermissionsByServiceName(String serviceName);

    List<RolePermissionEntity> getRolePermissionsByServiceNameForIO(String serviceName);

    List<RolePermissionEntity> getRolePermissionsByRoleIdAndServiceName(@Param("roleId") Long roleId, @Param("serviceName") String serviceName);

    List<Long> getPermissionIdsByRoleId(Long roleId);

    List<Long> getRoleIdsByPermissionId(Long permissionId);

    void insertRolePermission(RolePermissionEntity rolePermission);

    void insertUpdateRolePermission(RolePermissionEntity rolePermission);

    void insertRolePermissions(List<RolePermissionEntity> rolePermissions);

    void insertUpdateRolePermissions(List<RolePermissionEntity> rolePermissions);

    void updateRolePermission(RolePermissionEntity rolePermission);

    void deleteRolePermission(Long id);

    void deleteRolePermissionByRoleIdAndPermissionId(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

    void deleteRolePermissionByRoleId(Long roleId);

    void deleteRolePermissions(List<RolePermissionEntity> rolePermissions);

    // 判断该角色是否被引用。0为未引用，1为引用
    int containsRole(Long roleId);

    // 判断该权限是否被引用。0为未引用，1为引用
    int containsPermission(Long permissionId);
}