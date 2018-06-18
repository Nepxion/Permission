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
    @RequestMapping(value = "/permission/getAllPermissionTypes", method = RequestMethod.GET)
    PermissionType[] getAllPermissionTypes();

    @RequestMapping(value = "/permission/getPermission/{id}", method = RequestMethod.GET)
    PermissionEntity getPermission(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "/permission/getAllPermissions", method = RequestMethod.GET)
    List<PermissionEntity> getAllPermissions();

    @RequestMapping(value = "/permission/getPermissions", method = RequestMethod.POST)
    List<PermissionEntity> getPermissions(@RequestBody List<Long> ids);

    @RequestMapping(value = "/permission/getPermissionsByServiceName/{serviceName}", method = RequestMethod.GET)
    List<PermissionEntity> getPermissionsByServiceName(@PathVariable(value = "serviceName") String serviceName);

    @RequestMapping(value = "/permission/getPermissionsByTypeAndServiceName/{type}/{serviceName}", method = RequestMethod.GET)
    List<PermissionEntity> getPermissionsByTypeAndServiceName(@PathVariable(value = "type") String type, @PathVariable(value = "serviceName") String serviceName);

    @RequestMapping(value = "/permission/getPermissionByNameAndTypeAndServiceName/{name}/{type}/{serviceName}", method = RequestMethod.GET)
    PermissionEntity getPermissionByNameAndTypeAndServiceName(@PathVariable(value = "name") String name, @PathVariable(value = "type") String type, @PathVariable(value = "serviceName") String serviceName);

    @RequestMapping(value = "/permission/getPermissionsByResources", method = RequestMethod.POST)
    List<PermissionEntity> getPermissionsByResources(@RequestBody List<String> resources);

    @RequestMapping(value = "/permission/getPermissionsByRoleId/{roleId}", method = RequestMethod.GET)
    List<PermissionEntity> getPermissionsByRoleId(@PathVariable(value = "roleId") Long roleId);

    @RequestMapping(value = "/permission/getPermissionsByRoleIds", method = RequestMethod.POST)
    List<PermissionEntity> getPermissionsByRoleIds(@RequestBody List<Long> roleIds);

    @RequestMapping(value = "/permission/insertPermission", method = RequestMethod.POST)
    PermissionEntity insertPermission(@RequestBody PermissionEntity permission);

    @RequestMapping(value = "/permission/insertUpdatePermission", method = RequestMethod.POST)
    PermissionEntity insertUpdatePermission(@RequestBody PermissionEntity permission);

    @RequestMapping(value = "/permission/insertPermissions", method = RequestMethod.POST)
    List<PermissionEntity> insertPermissions(@RequestBody List<PermissionEntity> permissions);

    @RequestMapping(value = "/permission/insertUpdatePermissions", method = RequestMethod.POST)
    List<PermissionEntity> insertUpdatePermissions(@RequestBody List<PermissionEntity> permissions);

    @RequestMapping(value = "/permission/updatePermission", method = RequestMethod.PUT)
    void updatePermission(@RequestBody PermissionEntity permission);

    @RequestMapping(value = "/permission/deletePermission/{id}", method = RequestMethod.DELETE)
    void deletePermission(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "/permission/deletePermissions", method = RequestMethod.POST)
    void deletePermissions(@RequestBody List<Long> ids);

    @RequestMapping(value = "/permission/persist", method = RequestMethod.POST)
    void persist(@RequestBody List<PermissionEntity> permissions);
}