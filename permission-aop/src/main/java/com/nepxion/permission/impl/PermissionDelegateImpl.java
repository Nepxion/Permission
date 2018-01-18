package com.nepxion.permission.impl;

/**
 * <p>Title: Nepxion Permission</p>
 * <p>Description: Nepxion Permission</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.nepxion.permission.PermissionDelegate;
import com.nepxion.permission.entity.PermissionEntity;
import com.nepxion.permission.entity.UserEntity;

@Service
public class PermissionDelegateImpl implements PermissionDelegate {
    @Override
    public void persist(List<PermissionEntity> permissionEntityList) {
        
    }

    @Override
    public boolean authorize(String userId, String userType, String permissionName, String permissionType, String serviceName) {
        if (StringUtils.equals(userId, "zhangsan")) {
            return true;
        } else if (StringUtils.equals(userId, "lisi")) {
            return false;
        }
        
        return true;
    }

    @Override
    public UserEntity getUserEntity(String token) {
        if (StringUtils.equals(token, "abcd1234")) {
            UserEntity userEntity = new UserEntity();
            userEntity.setUserId("lisi");
            userEntity.setUserType("LDAP");

            return userEntity;
        }

        return null;
    }
}