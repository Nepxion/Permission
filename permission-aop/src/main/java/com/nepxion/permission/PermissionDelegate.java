package com.nepxion.permission;

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

public interface PermissionDelegate {
    void persist(List<PermissionEntity> permissionEntityList);

    boolean authorize(String userId, String userType, String permissionName, String permissionType, String serviceName);

    UserEntity getUserEntity(String token);
}