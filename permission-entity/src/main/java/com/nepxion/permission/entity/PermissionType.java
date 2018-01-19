package com.nepxion.permission.entity;

/**
 * <p>Title: Nepxion Permission</p>
 * <p>Description: Nepxion Permission</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class PermissionType implements Serializable {
    private static final long serialVersionUID = 6102187790951666119L;

    public static final PermissionType API = new PermissionType("接口级", "API");
    public static final PermissionType GATEWAY = new PermissionType("网关级", "GATEWAY");
    public static final PermissionType UI = new PermissionType("界面级", "UI");

    private String name;
    private String value;

    public PermissionType() {

    }

    public PermissionType(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public PermissionType fromString(String value) {
        if (StringUtils.equalsIgnoreCase(value, API.getValue())) {
            return API;
        } else if (StringUtils.equalsIgnoreCase(value, GATEWAY.getValue())) {
            return GATEWAY;
        } else if (StringUtils.equalsIgnoreCase(value, UI.getValue())) {
            return UI;
        }

        return null;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object object) {
        return EqualsBuilder.reflectionEquals(this, object);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}