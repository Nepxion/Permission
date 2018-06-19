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

import com.nepxion.permission.entity.RoleEntity;

@FeignClient(value = "${permission.service.name}")
public interface RoleResource {
    @RequestMapping(value = "/role/getRole/{id}", method = RequestMethod.GET)
    RoleEntity getRole(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "/role/getAllRoles", method = RequestMethod.GET)
    List<RoleEntity> getAllRoles();

    @RequestMapping(value = "/role/getRoles", method = RequestMethod.POST)
    List<RoleEntity> getRoles(@RequestBody List<Long> ids);

    @RequestMapping(value = "/role/getRolesByServiceName/{serviceName}", method = RequestMethod.GET)
    List<RoleEntity> getRolesByServiceName(@PathVariable(value = "serviceName") String serviceName);

    @RequestMapping(value = "/role/getRoleByNameAndServiceName/{name}/{serviceName}", method = RequestMethod.GET)
    RoleEntity getRoleByNameAndServiceName(@PathVariable(value = "name") String name, @PathVariable(value = "serviceName") String serviceName);

    @RequestMapping(value = "/role/getRolesByPermissionId/{permissionId}", method = RequestMethod.GET)
    List<RoleEntity> getRolesByPermissionId(@PathVariable(value = "permissionId") Long permissionId);

    @RequestMapping(value = "/role/getRolesByPermissionIds", method = RequestMethod.POST)
    List<RoleEntity> getRolesByPermissionIds(@RequestBody List<Long> permissionIds);

    @RequestMapping(value = "/role/getRolesByUserIdAndUserType/{userId}/{userType}", method = RequestMethod.GET)
    List<RoleEntity> getRolesByUserIdAndUserType(@PathVariable(value = "userId") String userId, @PathVariable(value = "userType") String userType);

    @RequestMapping(value = "/role/indicateBoundRoles", method = RequestMethod.POST)
    List<RoleEntity> indicateBoundRoles(@RequestBody List<RoleEntity> roles);

    @RequestMapping(value = "/role/insertRole", method = RequestMethod.POST)
    RoleEntity insertRole(@RequestBody RoleEntity role);

    @RequestMapping(value = "/role/insertUpdateRole", method = RequestMethod.POST)
    RoleEntity insertUpdateRole(@RequestBody RoleEntity role);

    @RequestMapping(value = "/role/insertRoles", method = RequestMethod.POST)
    List<RoleEntity> insertRoles(@RequestBody List<RoleEntity> roles);

    @RequestMapping(value = "/role/insertUpdateRoles", method = RequestMethod.POST)
    void insertUpdateRoles(@RequestBody List<RoleEntity> roles);

    @RequestMapping(value = "/role/updateRole", method = RequestMethod.PUT)
    void updateRole(@RequestBody RoleEntity role);

    @RequestMapping(value = "/role/deleteRole/{id}", method = RequestMethod.DELETE)
    void deleteRole(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "/role/deleteRoles", method = RequestMethod.POST)
    void deleteRoles(@RequestBody List<Long> ids);
}