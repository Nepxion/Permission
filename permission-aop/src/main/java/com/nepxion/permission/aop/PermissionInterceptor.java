package com.nepxion.permission.aop;

/**
 * <p>Title: Nepxion Permission</p>
 * <p>Description: Nepxion Permission</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import java.lang.reflect.Method;

import javax.annotation.PostConstruct;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nepxion.aquarius.common.constant.AquariusConstant;
import com.nepxion.matrix.proxy.aop.AbstractInterceptor;
import com.nepxion.permission.annotation.Permission;
import com.nepxion.permission.annotation.Token;
import com.nepxion.permission.annotation.UserId;
import com.nepxion.permission.annotation.UserType;
import com.nepxion.permission.constant.PermissionConstant;
import com.nepxion.permission.delegate.PermissionDelegate;
import com.nepxion.permission.entity.PermissionType;
import com.nepxion.permission.entity.UserEntity;
import com.nepxion.permission.exception.PermissionException;

@Component("permissionInterceptor")
public class PermissionInterceptor extends AbstractInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(PermissionInterceptor.class);

    @Value("${" + AquariusConstant.FREQUENT_LOG_PRINT + "}")
    private Boolean frequentLogPrint;

    @Value("${" + PermissionConstant.PERMISSION_INTERCEPTION_ENABLED + ":true}")
    private Boolean interceptionEnabled;

    @Value("${" + PermissionConstant.SERVICE_NAME + "}")
    private String serviceName;

    @Value("${" + PermissionConstant.PERMISSION_USER_TYPE_WHITELIST + ":}")
    private String whitelist;

    // @Autowired(required = false)
    @Autowired
    private PermissionDelegate permissionDelegate;

    @Autowired
    private PermissionAuthorization permissionAuthorization;

    @PostConstruct
    public void initialize() {
        LOG.info("Permission interception enabled is {}...", interceptionEnabled);
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        if (interceptionEnabled) {
            Permission permissionAnnotation = getPermissionAnnotation(invocation);
            if (permissionAnnotation != null) {
                String name = permissionAnnotation.name();
                String label = permissionAnnotation.label();
                String description = permissionAnnotation.description();

                return invokePermission(invocation, name, label, description);
            }
        }

        return invocation.proceed();
    }

    private Permission getPermissionAnnotation(MethodInvocation invocation) {
        Method method = invocation.getMethod();
        if (method.isAnnotationPresent(Permission.class)) {
            return method.getAnnotation(Permission.class);
        }

        return null;
    }

    private Object invokePermission(MethodInvocation invocation, String name, String label, String description) throws Throwable {
        if (StringUtils.isEmpty(serviceName)) {
            throw new PermissionException("Service name is null or empty");
        }

        if (StringUtils.isEmpty(name)) {
            throw new PermissionException("Annotation [Permission]'s name is null or empty");
        }

        String proxyType = getProxyType(invocation);
        String proxiedClassName = getProxiedClassName(invocation);
        String methodName = getMethodName(invocation);

        if (frequentLogPrint) {
            LOG.info("Intercepted for annotation - Permission [name={}, label={}, description={}, proxyType={}, proxiedClass={}, method={}]", name, label, description, proxyType, proxiedClassName, methodName);
        }

        // 获取方法参数上的注解值
        String userId = getValueByParameterAnnotation(invocation, UserId.class, String.class);
        String userType = getValueByParameterAnnotation(invocation, UserType.class, String.class);
        String token = getValueByParameterAnnotation(invocation, Token.class, String.class);

        if (StringUtils.isEmpty(token) && (StringUtils.isEmpty(userId) || StringUtils.isEmpty(userType))) {
            throw new PermissionException("Annotation [Token] or [UserId] && [UserType] must be in target method");
        }

        // 根据token获取userId和userType
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(userType)) {
            UserEntity userEntity = permissionDelegate.getUserEntity(token);
            userId = userEntity.getUserId();
            userType = userEntity.getUserType();
        }

        // 检查用户类型白名单，决定某个类型的用户是否要执行权限验证拦截
        boolean checkUserTypeFilters = checkUserTypeFilters(userType);
        if (checkUserTypeFilters) {
            boolean authorized = permissionAuthorization.authorize(userId, userType, name, PermissionType.API.getValue(), serviceName);
            if (authorized) {
                return invocation.proceed();
            } else {
                String parameterTypesValue = getMethodParameterTypesValue(invocation);

                throw new PermissionException("No permision to proceed method [name=" + methodName + ", parameterTypes=" + parameterTypesValue + "], permissionName=" + name + ", permissionLabel=" + label);
            }
        }

        return invocation.proceed();
    }

    private boolean checkUserTypeFilters(String userType) {
        if (StringUtils.isEmpty(whitelist)) {
            return true;
        }

        if (whitelist.toLowerCase().indexOf(userType.toLowerCase()) > -1) {
            return true;
        }

        return false;
    }
}