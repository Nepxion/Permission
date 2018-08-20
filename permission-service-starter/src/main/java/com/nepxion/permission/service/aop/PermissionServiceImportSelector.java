package com.nepxion.permission.service.aop;

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
import com.nepxion.permission.service.annotation.EnablePermissionSerivce;
import com.nepxion.permission.service.constant.PermissionConstant;

@Order(Ordered.LOWEST_PRECEDENCE - 100)
public class PermissionServiceImportSelector extends AbstractImportSelector<EnablePermissionSerivce> {
    @Override
    protected boolean isEnabled() {
        return new RelaxedPropertyResolver(getEnvironment()).getProperty(PermissionConstant.PERMISSION_SERVICE_ENABLED, Boolean.class, Boolean.TRUE);
    }
}