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

import com.nepxion.permission.entity.RoleEntity;

public interface RoleMapper {
    RoleEntity getRole(Long id);

    List<RoleEntity> getAllRoles();

    List<RoleEntity> getRoles(List<Long> ids);

    List<RoleEntity> getRolesByServiceName(String serviceName);

    RoleEntity getRoleByNameAndServiceName(@Param("name") String name, @Param("serviceName") String serviceName);

    List<RoleEntity> getRolesByPermissionId(Long permissionId);

    List<RoleEntity> getRolesByPermissionIds(List<Long> permissionIds);

    List<RoleEntity> getRolesByUserIdAndUserType(@Param("userId") String userId, @Param("userType") String userType);

    void insertRole(RoleEntity role);

    void insertUpdateRole(RoleEntity role);

    void insertRoles(List<RoleEntity> roles);

    // 批量新增（如果存在则更新），返回的是第一条数据自增的值ֵ
    void insertUpdateRoles(List<RoleEntity> roles);

    void updateRole(RoleEntity role);

    void deleteRole(Long id);

    void deleteRoles(List<Long> ids);
}