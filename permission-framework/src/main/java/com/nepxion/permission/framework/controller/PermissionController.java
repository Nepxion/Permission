package com.nepxion.permission.framework.controller;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nepxion.permission.entity.PermissionEntity;
import com.nepxion.permission.entity.UserEntity;

@RestController
@Api(tags = { "权限接口" })
public class PermissionController {
    @PostConstruct
    private void initialize() {

    }

    @RequestMapping(value = "/persist", method = RequestMethod.POST)
    @ApiOperation(value = "权限列表入口接口", notes = "权限列表入口接口", response = Void.class, httpMethod = "POST")
    public void persist(List<PermissionEntity> permissionEntityList) {

    }

    public boolean authorize(String userId, String userType, String permissionName, String permissionType, String serviceName) {
        if (StringUtils.equals(userId, "zhangsan")) {
            return true;
        } else if (StringUtils.equals(userId, "lisi")) {
            return false;
        }

        return true;
    }

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