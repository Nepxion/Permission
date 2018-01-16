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
import java.util.Date;

public interface IEntity extends Serializable {
    Integer getId();

    void setId(Integer id);

    String getName();

    void setName(String name);

    String getLabel();

    void setLabel(String label);

    String getDescription();

    void setDescription(String description);

    String getServiceName();

    void setServiceName(String serviceName);

    String getCreateOwner();

    void setCreateOwner(String createOwner);

    Date getCreateTime();

    void setCreateTime(Date createTime);

    String getUpdateOwner();

    void setUpdateOwner(String updateOwner);

    Date getUpdateTime();

    void setUpdateTime(Date updateTime);
}