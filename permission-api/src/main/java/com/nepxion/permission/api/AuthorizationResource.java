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

import com.nepxion.permission.entity.RolePermissionEntity;
import com.nepxion.permission.entity.UserEntity;
import com.nepxion.permission.entity.UserRoleEntity;

@FeignClient(value = "${permission.service.name}")
public interface AuthorizationResource {
    @RequestMapping(value = "/authorization/getRolePermission/{id}", method = RequestMethod.GET)
    RolePermissionEntity getRolePermission(@PathVariable("id") Long id);

    @RequestMapping(value = "/authorization/getAllRolePermissions", method = RequestMethod.GET)
    List<RolePermissionEntity> getAllRolePermissions();

    @RequestMapping(value = "/authorization/getRolePermissions", method = RequestMethod.POST)
    List<RolePermissionEntity> getRolePermissions(@RequestBody List<Long> ids);

    @RequestMapping(value = "/authorization/getRolePermissionsByServiceName/{serviceName}", method = RequestMethod.GET)
    List<RolePermissionEntity> getRolePermissionsByServiceName(@PathVariable("serviceName") String serviceName);

    @RequestMapping(value = "/authorization/getRolePermissionsByRoleIdAndServiceName/{roleId}/{serviceName}", method = RequestMethod.GET)
    List<RolePermissionEntity> getRolePermissionsByRoleIdAndServiceName(@PathVariable("roleId") Long roleId, @PathVariable("serviceName") String serviceName);

    @RequestMapping(value = "/authorization/getPermissionIdsByRoleId/{roleId}", method = RequestMethod.GET)
    List<Long> getPermissionIdsByRoleId(@PathVariable("roleId") Long roleId);

    @RequestMapping(value = "/authorization/getRoleIdsByPermissionId/{permissionId}", method = RequestMethod.GET)
    List<Long> getRoleIdsByPermissionId(@PathVariable("permissionId") Long permissionId);

    @RequestMapping(value = "/authorization/bindRolePermission", method = RequestMethod.POST)
    RolePermissionEntity bindRolePermission(@RequestBody RolePermissionEntity rolePermission);

    @RequestMapping(value = "/authorization/bindUpdateRolePermission", method = RequestMethod.POST)
    void bindUpdateRolePermission(@RequestBody RolePermissionEntity rolePermission);

    @RequestMapping(value = "/authorization/bindRolePermissions", method = RequestMethod.POST)
    List<RolePermissionEntity> bindRolePermissions(@RequestBody List<RolePermissionEntity> rolePermissions);

    @RequestMapping(value = "/authorization/bindUpdateRolePermissions", method = RequestMethod.POST)
    void bindUpdateRolePermissions(@RequestBody List<RolePermissionEntity> rolePermissions);

    @RequestMapping(value = "/authorization/unbindRolePermission/{id}", method = RequestMethod.DELETE)
    void unbindRolePermission(@PathVariable("id") Long id);

    @RequestMapping(value = "/authorization/unbindRolePermission/{roleId}/{permissionId}", method = RequestMethod.DELETE)
    void unbindRolePermission(@PathVariable("roleId") Long roleId, @PathVariable("permissionId") Long permissionId);

    @RequestMapping(value = "/authorization/unbindRolePermissions", method = RequestMethod.POST)
    void unbindRolePermissions(@RequestBody List<RolePermissionEntity> rolePermissions);

    @RequestMapping(value = "/authorization/unbindRolePermissionsByRoleId/{roleId}", method = RequestMethod.DELETE)
    void unbindRolePermissionsByRoleId(@PathVariable("roleId") Long roleId);

    @RequestMapping(value = "/authorization/updateRolePermission", method = RequestMethod.PUT)
    void updateRolePermission(@RequestBody RolePermissionEntity rolePermission);

    @RequestMapping(value = "/authorization/getUserRole/{id}", method = RequestMethod.GET)
    UserRoleEntity getUserRole(@PathVariable("id") Long id);

    @RequestMapping(value = "/authorization/getAllUserRoles", method = RequestMethod.GET)
    List<UserRoleEntity> getAllUserRoles();

    @RequestMapping(value = "/authorization/getUserRoles", method = RequestMethod.POST)
    List<UserRoleEntity> getUserRoles(@RequestBody List<Long> ids);

    @RequestMapping(value = "/authorization/getUserRolesByServiceName/{serviceName}", method = RequestMethod.GET)
    List<UserRoleEntity> getUserRolesByServiceName(@PathVariable("serviceName") String serviceName);

    @RequestMapping(value = "/authorization/getUserRolesByUserIdAndUserTypeAndServiceName/{userId}/{userType}/{serviceName}", method = RequestMethod.GET)
    List<UserRoleEntity> getUserRolesByUserIdAndUserTypeAndServiceName(@PathVariable("userId") String userId, @PathVariable("userType") String userType, @PathVariable("serviceName") String serviceName);

    @RequestMapping(value = "/authorization/getRoleIdsByUserIdAndUserType/{userId}/{userType}", method = RequestMethod.GET)
    List<Long> getRoleIdsByUserIdAndUserType(@PathVariable("userId") String userId, @PathVariable("userType") String userType);

    @RequestMapping(value = "/authorization/getUsersByRoleId/{roleId}", method = RequestMethod.GET)
    List<UserEntity> getUsersByRoleId(@PathVariable("roleId") Long roleId);

    @RequestMapping(value = "/authorization/getUserRolesByPermissonId/{permissonId}", method = RequestMethod.GET)
    List<UserRoleEntity> getUserRolesByPermissonId(@PathVariable("permissonId") Long permissionId);

    @RequestMapping(value = "/authorization/bindUserRole", method = RequestMethod.POST)
    UserRoleEntity bindUserRole(@RequestBody UserRoleEntity userRole);

    @RequestMapping(value = "/authorization/bindUpdateUserRole", method = RequestMethod.POST)
    void bindUpdateUserRole(@RequestBody UserRoleEntity userRole);

    @RequestMapping(value = "/authorization/bindUserRoles", method = RequestMethod.POST)
    List<UserRoleEntity> bindUserRoles(@RequestBody List<UserRoleEntity> userRoles);

    @RequestMapping(value = "/authorization/bindUpdateUserRoles", method = RequestMethod.POST)
    void bindUpdateUserRoles(@RequestBody List<UserRoleEntity> userRoles);

    @RequestMapping(value = "/authorization/unbindUserRole/{id}", method = RequestMethod.DELETE)
    void unbindUserRole(@PathVariable("id") Long id);

    @RequestMapping(value = "/authorization/unbindUserRole/{userId}/{userType}/{roleId}", method = RequestMethod.DELETE)
    void unbindUserRole(@PathVariable("userId") String userId, @PathVariable("userType") String userType, @PathVariable("roleId") Long roleId);

    @RequestMapping(value = "/authorization/unbindUserRoles", method = RequestMethod.POST)
    void unbindUserRoles(@RequestBody List<UserRoleEntity> userRoles);

    @RequestMapping(value = "/unbindUserRolesByUserIdAndUserType/{userId}/{userType}", method = RequestMethod.DELETE)
    void unbindUserRolesByUserIdAndUserType(@PathVariable("userId") String userId, @PathVariable("userType") String userType);

    @RequestMapping(value = "/authorization/updateUserRole", method = RequestMethod.PUT)
    void updateUserRole(@RequestBody UserRoleEntity userRole);

    @RequestMapping(value = "/authorization/authorize/{userId}/{userType}/{permissionName}/{permissionType}/{serviceName}", method = RequestMethod.GET)
    boolean authorize(@PathVariable(value = "userId") String userId, @PathVariable(value = "userType") String userType, @PathVariable(value = "permissionName") String permissionName, @PathVariable(value = "permissionType") String permissionType, @PathVariable(value = "serviceName") String serviceName);
}