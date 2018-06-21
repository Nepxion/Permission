package com.nepxion.permission.aop;

/**
 * <p>Title: Nepxion Permission</p>
 * <p>Description: Nepxion Permission</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import com.nepxion.matrix.proxy.aop.DefaultAutoScanProxy;
import com.nepxion.matrix.proxy.mode.ProxyMode;
import com.nepxion.matrix.proxy.mode.ScanMode;
import com.nepxion.matrix.proxy.util.ProxyUtil;
import com.nepxion.permission.annotation.Permission;
import com.nepxion.permission.constant.PermissionConstant;
import com.nepxion.permission.entity.PermissionEntity;
import com.nepxion.permission.entity.PermissionType;
import com.nepxion.permission.exception.PermissionAopException;

public class PermissionAutoScanProxy extends DefaultAutoScanProxy {
    private static final long serialVersionUID = 3188054573736878865L;

    static {
        System.out.println("");
        System.out.println("╔═══╗");
        System.out.println("║╔═╗║");
        System.out.println("║╚═╝╠══╦═╦╗╔╦╦══╦══╦╦══╦══╗");
        System.out.println("║╔══╣║═╣╔╣╚╝╠╣══╣══╬╣╔╗║╔╗║");
        System.out.println("║║  ║║═╣║║║║║╠══╠══║║╚╝║║║║");
        System.out.println("╚╝  ╚══╩╝╚╩╩╩╩══╩══╩╩══╩╝╚╝");
        System.out.println("Nepxion Permission  v1.0.12");
        System.out.println("");
    }

    @Value("${" + PermissionConstant.PERMISSION_AUTOMATIC_PERSIST_ENABLED + ":true}")
    private Boolean automaticPersistEnabled;

    @Value("${" + PermissionConstant.SERVICE_NAME + "}")
    private String serviceName;

    @Value("${" + PermissionConstant.SERVICE_OWNER + ":Unknown}")
    private String owner;

    private String[] commonInterceptorNames;

    @SuppressWarnings("rawtypes")
    private Class[] methodAnnotations;

    private List<PermissionEntity> permissions = new ArrayList<PermissionEntity>();

    public PermissionAutoScanProxy(String scanPackages) {
        super(scanPackages, ProxyMode.BY_METHOD_ANNOTATION_ONLY, ScanMode.FOR_METHOD_ANNOTATION_ONLY);
    }

    @Override
    protected String[] getCommonInterceptorNames() {
        if (commonInterceptorNames == null) {
            commonInterceptorNames = new String[] { "permissionInterceptor" };
        }

        return commonInterceptorNames;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Class<? extends Annotation>[] getMethodAnnotations() {
        if (methodAnnotations == null) {
            methodAnnotations = new Class[] { Permission.class };
        }

        return methodAnnotations;
    }

    @Override
    protected void methodAnnotationScanned(Class<?> targetClass, Method method, Class<? extends Annotation> methodAnnotation) {
        if (automaticPersistEnabled) {
            if (methodAnnotation == Permission.class) {
                Permission permissionAnnotation = method.getAnnotation(Permission.class);

                String name = permissionAnnotation.name();
                if (StringUtils.isEmpty(name)) {
                    throw new PermissionAopException("Annotation [Permission]'s name is null or empty");
                }

                String label = permissionAnnotation.label();

                String description = permissionAnnotation.description();

                // 取类名、方法名和参数类型组合赋值
                String className = targetClass.getName();
                String methodName = method.getName();
                Class<?>[] parameterTypes = method.getParameterTypes();
                String parameterTypesValue = ProxyUtil.toString(parameterTypes);
                String resource = className + "." + methodName + "(" + parameterTypesValue + ")";

                PermissionEntity permission = new PermissionEntity();
                permission.setName(name);
                permission.setLabel(label);
                permission.setType(PermissionType.API.getValue());
                permission.setDescription(description);
                permission.setServiceName(serviceName);
                permission.setResource(resource);
                permission.setCreateOwner(owner);
                permission.setUpdateOwner(owner);

                permissions.add(permission);
            }
        }
    }

    public List<PermissionEntity> getPermissions() {
        return permissions;
    }
}