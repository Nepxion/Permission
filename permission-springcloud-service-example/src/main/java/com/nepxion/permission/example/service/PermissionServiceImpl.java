package com.nepxion.permission.example.service;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nepxion.permission.entity.PermissionEntity;
import com.nepxion.permission.entity.UserEntity;

// 该接口实现提供给调用端的Feign接口，需要实现的逻辑是权限数据入库，验证，以及缓存的操作
@RestController
public class PermissionServiceImpl {
    private static final Logger LOG = LoggerFactory.getLogger(PermissionServiceImpl.class);

    // 权限列表入库
    @RequestMapping(value = "/persist", method = RequestMethod.POST)
    public void persist(@RequestBody List<PermissionEntity> permissionEntityList) {
        // 实现权限扫描结果到数据库的入库
        // 需要注意，权限的重复入库问题，一般遵循“不存在则插入，存在则覆盖”的原则
        LOG.info("权限列表入库：{}", permissionEntityList);
    }

    // 权限验证
    @RequestMapping(value = "/authorize/{userId}/{userType}/{permissionName}/{permissionType}/{serviceName}", method = RequestMethod.GET)
    public boolean authorize(
            @PathVariable(value = "userId") String userId,
            @PathVariable(value = "userType") String userType,
            @PathVariable(value = "permissionName") String permissionName,
            @PathVariable(value = "permissionType") String permissionType,
            @PathVariable(value = "serviceName") String serviceName) {
        // 验证用户是否有权限
        // 需要和用户系统做对接，userId一般为登录名，userType为用户系统类型。目前支持多用户类型，所以通过userType来区分同名登录用户，例如财务系统有用户叫zhangsan，支付系统也有用户叫zhangsan
        // permissionName即在@Permission注解上定义的name，permissionType为权限类型，目前支持接口权限(API)，网关权限(GATEWAY)，界面权限(UI)三种类型的权限(参考PermissionType.java类的定义)
        // serviceName即服务名，在application.properties里定义的spring.application.name
        // 对于验证结果，在后端实现分布式缓存，可以避免频繁调用数据库而出现性能问题
        // 示例描述用户zhangsan有权限，用户lisi没权限
        if (StringUtils.equals(userId, "zhangsan")) {
            return true;
        } else if (StringUtils.equals(userId, "lisi")) {
            return false;
        }

        return true;
    }

    // 根据Token获取User实体
    @RequestMapping(value = "/getUserEntity/{token}", method = RequestMethod.GET)
    public UserEntity getUserEntity(@PathVariable(value = "token") String token) {
        // 当前端登录后，它希望送token到后端，查询出用户信息(并以此调用authorize接口做权限验证，permission-aop已经实现，使用者并不需要关心)
        // 需要和单点登录系统，例如OAuth或者JWT等系统做对接
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