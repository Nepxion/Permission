package com.nepxion.permission.example;

/**
 * <p>Title: Nepxion Permission</p>
 * <p>Description: Nepxion Permission</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import com.nepxion.permission.annotation.Permission;
import com.nepxion.permission.annotation.UserId;
import com.nepxion.permission.annotation.UserType;

public interface UserService {
    @Permission(name = "Add User", label = "添加用户")
    boolean addUser(@UserId String userId, @UserType String userType, String user);

    @Permission(name = "Delete User", label = "删除用户", description = "删除用户")
    boolean deleteUser(@UserId String userId, @UserType String userType, String user, String org);
}