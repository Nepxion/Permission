package com.nepxion.permission.constant;

/**
 * <p>Title: Nepxion Permission</p>
 * <p>Description: Nepxion Permission</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

public class PermissionFeignConstant {
    public static final String TOKEN = "token";
    public static final String USER_ID = "userId";
    public static final String USER_TYPE = "userType";

    public static final String PERMISSION_FEIGN_HEADERS = TOKEN + ";" + USER_ID + ";" + USER_TYPE;
}