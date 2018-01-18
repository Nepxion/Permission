package com.nepxion.permission.service;

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

// 该接口实现可以跟随业务系统远程调用数据库，也可以通过调用远程独立的权限微服务的API(建议)来实现
@Service
public class MyDelegateImpl implements PermissionDelegate {
    // 权限列表入库
    @Override
    public void persist(List<PermissionEntity> permissionEntityList) {
        // 实现权限扫描结果到数据库的入库
        // 需要注意，权限的重复入库问题，一般遵循“不存在则插入，存在则覆盖”的原则
    }

    // 权限验证
    @Override
    public boolean authorize(String userId, String userType, String permissionName, String permissionType, String serviceName) {
        // 验证用户是否有权限
        // 示例描述用户zhangsan有权限，用户lisi没权限
        if (StringUtils.equals(userId, "zhangsan")) {
            return true;
        } else if (StringUtils.equals(userId, "lisi")) {
            return false;
        }

        return true;
    }

    // 根据Token获取User实体
    @Override
    public UserEntity getUserEntity(String token) {
        // 当前端登录后，它希望送token到后端，查询出用户信息(并以此调用authorize接口做权限验证，permission-aop已经实现，使用者并不需要关心)
        // 示例描述token为abcd1234对应的用户为lisi
        if (StringUtils.equals(token, "abcd1234")) {
            UserEntity userEntity = new UserEntity();
            userEntity.setUserId("lisi");
            userEntity.setUserType("LDAP");

            return userEntity;
        }

        return null;
    }
}