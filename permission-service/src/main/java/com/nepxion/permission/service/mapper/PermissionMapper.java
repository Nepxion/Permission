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

import com.nepxion.permission.entity.PermissionEntity;

public interface PermissionMapper {
    PermissionEntity getPermission(Integer id);

    List<PermissionEntity> getAllPermissions();

    List<PermissionEntity> getPermissions(List<Integer> ids);

    List<PermissionEntity> getPermissionsByServiceName(String serviceName);

    List<PermissionEntity> getPermissionsByTypeAndServiceName(@Param("type") String type, @Param("serviceName") String serviceName);

    PermissionEntity getPermissionByNameAndTypeAndServiceName(@Param("name") String name, @Param("type") String type, @Param("serviceName") String serviceName);

    List<PermissionEntity> getPermissionsByResources(List<String> resources);

    List<PermissionEntity> getPermissionsByRoleId(Integer roleId);

    List<PermissionEntity> getPermissionsByRoleIds(List<Integer> roleIds);

    void insertPermission(PermissionEntity permission);

    void insertUpdatePermission(PermissionEntity permission);

    void insertPermissions(List<PermissionEntity> permissions);

    // 批量新增（如果存在则更新），返回的是第一条数据自增的值
    void insertUpdatePermissions(List<PermissionEntity> permissions);

    void updatePermission(PermissionEntity permission);

    void deletePermission(Integer id);

    void deletePermissions(List<Integer> ids);

    List<Integer> authorize(@Param("userId") String userId, @Param("userType") String userType, @Param("permissionName") String permissionName, @Param("permissionType") String permissionType, @Param("serviceName") String serviceName);
}