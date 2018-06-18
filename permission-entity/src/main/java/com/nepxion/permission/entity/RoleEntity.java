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

public class RoleEntity extends BasicEntity {
    private static final long serialVersionUID = -4116501863109621719L;

    private String boundStatus = BoundStatus.UNKNOWN.getValue();

    public String getBoundStatus() {
        return boundStatus;
    }

    public void setBoundStatus(String boundStatus) {
        if (!StringUtils.equals(boundStatus, BoundStatus.YES.getValue()) && !StringUtils.equals(boundStatus, BoundStatus.NO.getValue()) && !StringUtils.equals(boundStatus, BoundStatus.UNKNOWN.getValue())) {
            throw new IllegalArgumentException("Mismatched bound status with value=" + boundStatus);
        }

        this.boundStatus = boundStatus;
    }
}