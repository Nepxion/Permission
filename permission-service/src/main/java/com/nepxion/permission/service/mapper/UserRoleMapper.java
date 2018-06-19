package com.nepxion.permission.service.mapper;

/**
 * <p>Title: Nepxion Permission</p>
 * <p>Description: Nepxion Permission</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nepxion.permission.entity.UserEntity;
import com.nepxion.permission.entity.UserRoleEntity;

public interface UserRoleMapper {
    UserRoleEntity getUserRole(Long id);

    List<UserRoleEntity> getAllUserRoles();
    
    List<UserRoleEntity> getUserRoles(List<Long> ids);

    List<UserRoleEntity> getUserRolesByServiceName(String serviceName);

    List<UserRoleEntity> getUserRolesByServiceNameForIO(String serviceName);

    List<UserRoleEntity> getUserRolesByUserIdAndUserTypeAndServiceName(@Param("userId") String userId, @Param("userType") String userType, @Param("serviceName") String serviceName);

    List<Long> getRoleIdsByUserIdAndUserType(@Param("userId") String userId, @Param("userType") String userType);

    List<UserEntity> getUsersByRoleId(Long roleId);

    List<UserRoleEntity> getUserRolesByPermissonId(Long permissionId);

    void insertUserRole(UserRoleEntity userRole);

    void insertUpdateUserRole(UserRoleEntity userRole);

    void insertUserRoles(List<UserRoleEntity> userRole);

    void insertUpdateUserRoles(List<UserRoleEntity> userRoles);

    void updateUserRole(UserRoleEntity userRole);

    void deleteUserRole(Long id);

    void deleteUserRoleByUserIdAndUserTypeAndRoleId(@Param("userId") String userId, @Param("userType") String userType, @Param("roleId") Long roleId);

    void deleteUserRolesByUserIdAndUserType(@Param("userId") String userId, @Param("userType") String userType);

    void deleteUserRoles(List<UserRoleEntity> userRoles);

    // 判断该角色是否被引用。0为未引用，1为引用
    int containsRole(Long roleId);
}