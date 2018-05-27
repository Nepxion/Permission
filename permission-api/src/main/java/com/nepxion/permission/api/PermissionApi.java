package com.nepxion.permission.api;

/**
 * <p>Title: Nepxion Permission</p>
 * <p>Description: Nepxion Permission</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import java.util.List;

import com.nepxion.permission.entity.PermissionEntity;
import com.nepxion.permission.entity.UserEntity;

public interface PermissionApi {
    // 权限列表入库
    void persist(List<PermissionEntity> permissionEntityList);

    // 权限验证
    boolean authorize(String userId, String userType, String permissionName, String permissionType, String serviceName);

    // 根据Token获取User实体
    UserEntity getUserEntity(String token);
}