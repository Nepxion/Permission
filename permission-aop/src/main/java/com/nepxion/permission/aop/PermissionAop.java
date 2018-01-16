package com.nepxion.permission.aop;

/**
 * <p>Title: Nepxion Permission</p>
 * <p>Description: Nepxion Permission</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.nepxion.permission.constant.PermissionConstant;

@Component("permissionAop")
public class PermissionAop {
    @Value("${" + PermissionConstant.PERMISSION_SCAN_PACKAGES + "}")
    private String scanPackages;

    @Bean("permissionAutoScanProxy")
    public PermissionAutoScanProxy permissionAutoScanProxy() {
        return new PermissionAutoScanProxy(scanPackages);
    }
}