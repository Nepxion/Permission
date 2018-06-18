package com.nepxion.permission.entity;

/**
 * <p>Title: Nepxion Permission</p>
 * <p>Description: Nepxion Permission</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class PermissionEntity extends BasicEntity {
    private static final long serialVersionUID = -82541551905707852L;

    private String type;
    private String resource;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (!StringUtils.equals(type, PermissionType.API.getValue()) && !StringUtils.equals(type, PermissionType.GATEWAY.getValue()) && !StringUtils.equals(type, PermissionType.UI.getValue())) {
            throw new IllegalArgumentException("Mismatched type with value=" + type);
        }

        this.type = type;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    // 校验权限名，只能是字母，数字，空格，下划线，中划线这5种组合
    public void validateName() {
        String name = getName();
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("Permission name can't be null or empty");
        }

        String regEx = "^[\\s0-9a-zA-Z_.-]+$"; // 只能包含字母，数字，空格，下划线，中划线，英文句号
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(name);

        boolean matched = matcher.matches();
        if (!matched) {
            throw new IllegalArgumentException("Permission name is only allowed to hold one of the followings : [A-Z, a-z, 0-9, blank, _, -, .]");
        }
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