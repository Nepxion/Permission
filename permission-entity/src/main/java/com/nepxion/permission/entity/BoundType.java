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

public class BoundType extends BasicType {
    private static final long serialVersionUID = -7679369513624608496L;
    
    public static final BoundType YES = new BoundType("已绑定", "YES");
    public static final BoundType NO = new BoundType("未绑定", "NO");
    public static final BoundType UNKNOWN = new BoundType("未知", "UNKNOWN");

    public BoundType() {
        super();
    }

    public BoundType(String name, String value) {
        super(name, value);
    }

    public BoundType fromString(String value) {
        if (StringUtils.equalsIgnoreCase(value, YES.getValue())) {
            return YES;
        } else if (StringUtils.equalsIgnoreCase(value, NO.getValue())) {
            return NO;
        } else if (StringUtils.equalsIgnoreCase(value, UNKNOWN.getValue())) {
            return UNKNOWN;
        }

        return null;
    }
}