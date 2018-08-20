package com.nepxion.permission.feign.aop;

/**
 * <p>Title: Nepxion Permission</p>
 * <p>Description: Nepxion Permission</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import com.nepxion.matrix.selector.AbstractImportSelector;
import com.nepxion.matrix.selector.RelaxedPropertyResolver;
import com.nepxion.permission.constant.PermissionFeignConstant;
import com.nepxion.permission.feign.annotation.EnablePermissionFeign;

@Order(Ordered.LOWEST_PRECEDENCE - 100)
public class PermissionFeignImportSelector extends AbstractImportSelector<EnablePermissionFeign> {
    @Override
    protected boolean isEnabled() {
        return new RelaxedPropertyResolver(getEnvironment()).getProperty(PermissionFeignConstant.PERMISSION_FEIGN_ENABLED, Boolean.class, Boolean.TRUE);
    }
}