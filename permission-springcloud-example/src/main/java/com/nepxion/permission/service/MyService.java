package com.nepxion.permission.service;

/**
 * <p>Title: Nepxion Permission</p>
 * <p>Description: Nepxion Permission</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import com.nepxion.permission.annotation.Permission;
import com.nepxion.permission.annotation.Token;
import com.nepxion.permission.annotation.UserId;
import com.nepxion.permission.annotation.UserType;

public interface MyService {
    @Permission(name = "A-Permission", label = "A权限")
    int doA(@UserId String userId, @UserType String userType, String value);

    @Permission(name = "B-Permission", label = "B权限", description = "B权限的描述")
    String doB(@Token String token, String value);
}