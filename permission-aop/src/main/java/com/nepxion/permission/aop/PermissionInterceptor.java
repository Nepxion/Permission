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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.nepxion.aquarius.common.constant.AquariusConstant;
import com.nepxion.matrix.proxy.aop.AbstractInterceptor;
import com.nepxion.permission.annotation.Permission;
import com.nepxion.permission.annotation.Token;
import com.nepxion.permission.annotation.UserId;
import com.nepxion.permission.annotation.UserType;
import com.nepxion.permission.api.UserResource;
import com.nepxion.permission.constant.PermissionConstant;
import com.nepxion.permission.entity.PermissionType;
import com.nepxion.permission.entity.UserEntity;
import com.nepxion.permission.exception.PermissionAopException;

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

    @Autowired
    private UserResource userResource;

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
            throw new PermissionAopException("Service name is null or empty");
        }

        if (StringUtils.isEmpty(name)) {
            throw new PermissionAopException("Annotation [Permission]'s name is null or empty");
        }

        String proxyType = getProxyType(invocation);
        String proxiedClassName = getProxiedClassName(invocation);
        String methodName = getMethodName(invocation);

        if (frequentLogPrint) {
            LOG.info("Intercepted for annotation - Permission [name={}, label={}, description={}, proxyType={}, proxiedClass={}, method={}]", name, label, description, proxyType, proxiedClassName, methodName);
        }

        UserEntity user = getUserEntityByIdAndType(invocation);
        if (user == null) {
            user = getUserEntityByToken(invocation);
        }

        if (user == null) {
            throw new PermissionAopException("No user context found");
        }

        String userId = user.getUserId();
        String userType = user.getUserType();

        // 检查用户类型白名单，决定某个类型的用户是否要执行权限验证拦截
        boolean checkUserTypeFilters = checkUserTypeFilters(userType);
        if (checkUserTypeFilters) {
            boolean authorized = permissionAuthorization.authorize(userId, userType, name, PermissionType.API.getValue(), serviceName);
            if (authorized) {
                return invocation.proceed();
            } else {
                String parameterTypesValue = getMethodParameterTypesValue(invocation);

                throw new PermissionAopException("No permision to proceed method [name=" + methodName + ", parameterTypes=" + parameterTypesValue + "], permissionName=" + name + ", permissionLabel=" + label);
            }
        }

        return invocation.proceed();
    }

    private UserEntity getUserEntityByIdAndType(MethodInvocation invocation) {
        // 获取方法参数上的注解值
        String userId = getValueByParameterAnnotation(invocation, UserId.class, String.class);
        String userType = getValueByParameterAnnotation(invocation, UserType.class, String.class);

        if (StringUtils.isEmpty(userId) && StringUtils.isNotEmpty(userType)) {
            throw new PermissionAopException("Annotation [UserId]'s value is null or empty");
        }

        if (StringUtils.isNotEmpty(userId) && StringUtils.isEmpty(userType)) {
            throw new PermissionAopException("Annotation [UserType]'s value is null or empty");
        }

        if (StringUtils.isEmpty(userId) && StringUtils.isEmpty(userType)) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                userId = attributes.getRequest().getHeader(PermissionConstant.USER_ID);
                userType = attributes.getRequest().getHeader(PermissionConstant.USER_TYPE);
            }
        }

        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(userType)) {
            return null;
        }

        UserEntity user = new UserEntity();
        user.setUserId(userId);
        user.setUserType(userType);

        return user;
    }

    private UserEntity getUserEntityByToken(MethodInvocation invocation) {
        // 获取方法参数上的注解值
        String token = getValueByParameterAnnotation(invocation, Token.class, String.class);

        if (StringUtils.isEmpty(token)) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                token = attributes.getRequest().getHeader(PermissionConstant.TOKEN);
            }
        }

        if (StringUtils.isEmpty(token)) {
            return null;
        }

        // 根据token获取userId和userType
        UserEntity user = userResource.getUser(token);
        if (user == null) {
            throw new PermissionAopException("No user found for token=" + token);
        }

        return user;
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