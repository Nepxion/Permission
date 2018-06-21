package com.nepxion.permission.entity;

/**
 * <p>Title: Nepxion Permission</p>
 * <p>Description: Nepxion Permission</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import org.apache.commons.lang3.StringUtils;

public class PermissionType extends BasicType {
    private static final long serialVersionUID = 6102187790951666119L;

    public static final PermissionType API = new PermissionType("接口权限", "API");
    public static final PermissionType GATEWAY = new PermissionType("网关权限", "GATEWAY");
    public static final PermissionType UI = new PermissionType("界面权限", "UI");

    public PermissionType() {
        super();
    }

    public PermissionType(String name, String value) {
        super(name, value);
    }

    public static PermissionType fromString(String value) {
        if (StringUtils.equals(value, API.getValue())) {
            return API;
        } else if (StringUtils.equals(value, GATEWAY.getValue())) {
            return GATEWAY;
        } else if (StringUtils.equals(value, UI.getValue())) {
            return UI;
        }

        return null;
    }
}