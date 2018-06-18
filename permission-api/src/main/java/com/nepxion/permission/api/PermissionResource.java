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

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nepxion.permission.entity.PermissionEntity;
import com.nepxion.permission.entity.PermissionType;

@FeignClient(value = "${permission.service.name}")
public interface PermissionResource {
    // 获取权限类型列表
    @RequestMapping(value = "/permission/getPermissionTypes", method = RequestMethod.GET)
    PermissionType[] getPermissionTypes();

    // 权限列表入库
    @RequestMapping(value = "/permission/persist", method = RequestMethod.POST)
    void persist(@RequestBody List<PermissionEntity> permissionEntityList);

    // 权限验证
    @RequestMapping(value = "/permission/authorize/{userId}/{userType}/{permissionName}/{permissionType}/{serviceName}", method = RequestMethod.GET)
    boolean authorize(
            @PathVariable(value = "userId") String userId,
            @PathVariable(value = "userType") String userType,
            @PathVariable(value = "permissionName") String permissionName,
            @PathVariable(value = "permissionType") String permissionType,
            @PathVariable(value = "serviceName") String serviceName);
}