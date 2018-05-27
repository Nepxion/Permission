# Nepxion Permission
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg?label=license)](https://github.com/Nepxion/Permission/blob/master/LICENSE)
[![Maven Central](https://img.shields.io/maven-central/v/com.nepxion/permission.svg?label=maven%20central)](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.nepxion%22%20AND%20permission)
[![Javadocs](http://www.javadoc.io/badge/com.nepxion/permission-aop.svg)](http://www.javadoc.io/doc/com.nepxion/permission-aop)
[![Build Status](https://travis-ci.org/Nepxion/Permission.svg?branch=master)](https://travis-ci.org/Nepxion/Permission)

Nepxion Permission是一款基于Redis分布式缓存权限调用系统，实现对业务系统中API的权限控制。它采用Nepxion Matrix AOP框架进行切面架构，提供注解调用方式

## 简介

    1. 实现权限自动扫描入库（可通过配置文件开启关闭）
    2. 实现权限验证从分布式缓存和API调用获取两种方式（缓存获取可通过配置文件开启关闭）
    3. 实现权限验证走UserId和Token两种方式，通过注解来决定
    4. 实现提供Delegate接口实现到数据库数据交互的扩展
    5. 实现根据Java8的特性来获取注解对应方法上的变量名(不是变量类型)，支持标准反射和字节码CGLIG(ASM library)来获取，前者适用于接口代理，后者适用于类代理
       标准反射的方式，需要在IDE和Maven里设置"-parameters"的Compiler Argument。参考如下：
       1)Eclipse加"-parameters"参数：https://www.concretepage.com/java/jdk-8/java-8-reflection-access-to-parameter-names-of-method-and-constructor-with-maven-gradle-and-eclipse-using-parameters-compiler-argument
       2)Idea加"-parameters"参数：http://blog.csdn.net/royal_lr/article/details/52279993

### 注意

Nepxion Permission提供简单易用的AOP框架（参考permission-springcloud-client-example），并非是全面的权限管理和调用系统，鉴于不同公司有不同权限架构，那么使用者需要自行去实现如下模块（参考permission-springcloud-service-example）：

    1. 实现基于权限-角色-用户三层体系的数据库模型(Pojo类已在permission-entity里实现)，并提供相关的增删改查接口
    2. 实现基于界面的权限-角色-用户的操作功能
    3. 实现和相关用户系统等多对接
    4. 实现基于权限验证的分布式缓存功能，例如验证缓存和失效(如果使用者有这样的需求)
    5. 实现基于Token的权限验证功能，和相关单点登录系统等做对接(如果使用者有这样的需求)
    6. 实现提供UI权限和API GATEWAY权限的接入(如果使用者有这样的需求)

### 依赖

客户端依赖
```xml
<dependency>
  <groupId>com.nepxion</groupId>
  <artifactId>permission-aop</artifactId>
  <version>${permission.version}</version>
</dependency>
```

服务端依赖
```xml
<dependency>
  <groupId>com.nepxion</groupId>
  <artifactId>permission-entity</artifactId>
  <version>${permission.version}</version>
</dependency>
```

### 配置

客户端配置
```xml
# Spring cloud config
spring.application.name=permission-springcloud-client-example
server.port=1234
eureka.instance.metadataMap.owner=Haojun Ren
eureka.client.serviceUrl.defaultZone=http://10.0.75.1:9528/eureka/

# Permission config
# 权限系统的服务名，作为Feign的寻址名
permission.service.name=permission-springcloud-service-example
# 扫描含有@Permission注解的接口或者类所在目录（可以不配置，但如果不配置，则扫描全局，会稍微降低性能）
permission.scan.packages=com.nepxion.permission.client.service
# 如果开启，默认每次服务启动时候，会往权限系统的数据库插入权限（权限不存在则插入，权限存在则覆盖）
permission.automatic.persist.enabled=true
# 权限系统验证拦截的用户类型白名单（例如用户类型是LDAP，那么对LDAP的用户做权限验证拦截）,多个值以“;”分隔
permission.user.type.whitelist=LDAP
# 如果开启，先从分布式缓存去获取权限验证结果，如果缓存不存在，则调用后端去获取权限验证结果；如果关闭，每次调用后端去获取权限验证结果
permission.cache.invoke.enabled=true

# Cache config
prefix=permission
cache.type=redisCache
# 扫描含有@Cacheable，@CacheEvict，CachePut等注解的接口或者类所在目录（可以不配置，但如果不配置，则扫描全局，会稍微降低性能）
cache.scan.packages=com.nepxion.permission

# Frequent log print
frequent.log.print=true
```

## 示例
在接口上添加@Permission注解，实现API权限验证功能
```java
package com.nepxion.permission.service;

/**
 * <p>Title: Nepxion Permission</p>
 * <p>Description: Nepxion Permission</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import com.nepxion.permission.annotation.Permission;
import com.nepxion.permission.annotation.Token;
import com.nepxion.permission.annotation.UserId;
import com.nepxion.permission.annotation.UserType;

public interface MyService {
    // 基于userId和userType的权限验证
    @Permission(name = "A-Permission", label = "A权限", description = "A权限的描述")
    int doA(@UserId String userId, @UserType String userType, String value);

    // 基于token的权限验证
    @Permission(name = "B-Permission", label = "B权限", description = "B权限的描述")
    String doB(@Token String token, String value);
}
```

SpringCloud应用入口，需要导入PermissionConfig激活权限验证功能
```java
package com.nepxion.permission;

/**
 * <p>Title: Nepxion Permission</p>
 * <p>Description: Nepxion Permission</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;

import com.nepxion.aquarius.common.context.AquariusContextAware;
import com.nepxion.permission.service.MyService;

@SpringBootApplication
@Import({ com.nepxion.permission.config.PermissionConfig.class })
public class MyApplication {
    private static final Logger LOG = LoggerFactory.getLogger(MyApplication.class);

    public static void main(String[] args) {
        new SpringApplicationBuilder(MyApplication.class).web(true).run(args);

        MyService myService = AquariusContextAware.getBean(MyService.class);
        LOG.info("Result : {}", myService.doA("zhangsan", "LDAP", "valueA"));
        LOG.info("Result : {}", myService.doB("abcd1234", "valueB"));
    }
}
```

模拟实现权限对数据库的相关接口（简单示例）
```java
package com.nepxion.permission.service;

/**
 * <p>Title: Nepxion Permission</p>
 * <p>Description: Nepxion Permission</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nepxion.permission.entity.PermissionEntity;
import com.nepxion.permission.entity.UserEntity;

// 该接口实现提供给调用端的Feign接口，需要实现的逻辑是权限数据入库，验证，以及缓存的操作
@RestController
public class PermissionServiceImpl {
    private static final Logger LOG = LoggerFactory.getLogger(PermissionServiceImpl.class);

    // 权限列表入库
    @RequestMapping(value = "/persist", method = RequestMethod.POST)
    public void persist(@RequestBody List<PermissionEntity> permissionEntityList) {
        // 实现权限扫描结果到数据库的入库
        // 需要注意，权限的重复入库问题，一般遵循“不存在则插入，存在则覆盖”的原则
        LOG.info("权限列表入库：{}", permissionEntityList);
    }

    // 权限验证
    @RequestMapping(value = "/authorize/{userId}/{userType}/{permissionName}/{permissionType}/{serviceName}", method = RequestMethod.GET)
    public boolean authorize(
            @PathVariable(value = "userId") String userId,
            @PathVariable(value = "userType") String userType,
            @PathVariable(value = "permissionName") String permissionName,
            @PathVariable(value = "permissionType") String permissionType,
            @PathVariable(value = "serviceName") String serviceName) {
        // 验证用户是否有权限
        // 需要和用户系统做对接，userId一般为登录名，userType为用户系统类型。目前支持多用户类型，所以通过userType来区分同名登录用户，例如财务系统有用户叫zhangsan，支付系统也有用户叫zhangsan
        // permissionName即在@Permission注解上定义的name，permissionType为权限类型，目前支持接口权限(API)，网关权限(GATEWAY)，界面权限(UI)三种类型的权限(参考PermissionType.java类的定义)
        // serviceName即服务名，在application.properties里定义的spring.application.name
        // 对于验证结果，在后端实现分布式缓存，可以避免频繁调用数据库而出现性能问题
        // 示例描述用户zhangsan有权限，用户lisi没权限
        if (StringUtils.equals(userId, "zhangsan")) {
            return true;
        } else if (StringUtils.equals(userId, "lisi")) {
            return false;
        }

        return true;
    }

    // 根据Token获取User实体
    @RequestMapping(value = "/getUserEntity/{token}", method = RequestMethod.GET)
    public UserEntity getUserEntity(@PathVariable(value = "token") String token) {
        // 当前端登录后，它希望送token到后端，查询出用户信息(并以此调用authorize接口做权限验证，permission-aop已经实现，使用者并不需要关心)
        // 需要和单点登录系统，例如OAuth或者JWT等系统做对接
        // 示例描述token为abcd1234对应的用户为lisi
        if (StringUtils.equals(token, "abcd1234")) {
            UserEntity userEntity = new UserEntity();
            userEntity.setUserId("lisi");
            userEntity.setUserType("LDAP");

            return userEntity;
        }

        return null;
    }
}
```

调用结果
```java
permission 2018-01-18 17:18:33,382 INFO [main] c.n.p.a.PermissionInterceptor [PermissionInterceptor.java:103] - Intercepted for annotation - Permission [name=A-Permission, label=A权限, description=, proxyType=Reflective Aop Proxy, proxiedClass=com.nepxion.permission.service.MyServiceImpl, method=doA]
permission 2018-01-18 17:18:33,442 INFO [main] c.n.a.c.a.CacheInterceptor [CacheInterceptor.java:120] - Intercepted for annotation - Cacheable [key=permission_cache_zhangsan_LDAP_A-Permission_SERVICE_permission-springcloud-example, expire=-1, proxyType=Cglib Aop Proxy, proxiedClass=com.nepxion.permission.aop.PermissionAuthorization, method=authorizeCache]
permission 2018-01-18 17:18:33,582 INFO [main] c.n.a.c.r.i.RedisCacheDelegateImpl [RedisCacheDelegateImpl.java:54] - Before invocation, Cacheable key=permission_cache_zhangsan_LDAP_A-Permission_SERVICE_permission-springcloud-example, cache=true in Redis
permission 2018-01-18 17:18:33,582 INFO [main] c.n.p.s.MyServiceImpl [MyServiceImpl.java:22] - ===== doA被调用
permission 2018-01-18 17:18:33,582 INFO [main] c.n.p.MyApplication [MyApplication.java:30] - Result : 123
permission 2018-01-18 17:18:33,582 INFO [main] c.n.p.a.PermissionInterceptor [PermissionInterceptor.java:103] - Intercepted for annotation - Permission [name=B-Permission, label=B权限, description=B权限的描述, proxyType=Reflective Aop Proxy, proxiedClass=com.nepxion.permission.service.MyServiceImpl, method=doB]
permission 2018-01-18 17:18:33,584 INFO [main] c.n.a.c.a.CacheInterceptor [CacheInterceptor.java:120] - Intercepted for annotation - Cacheable [key=permission_cache_lisi_LDAP_B-Permission_SERVICE_permission-springcloud-example, expire=-1, proxyType=Cglib Aop Proxy, proxiedClass=com.nepxion.permission.aop.PermissionAuthorization, method=authorizeCache]
permission 2018-01-18 17:18:33,585 INFO [main] c.n.a.c.r.i.RedisCacheDelegateImpl [RedisCacheDelegateImpl.java:54] - Before invocation, Cacheable key=permission_cache_lisi_LDAP_B-Permission_SERVICE_permission-springcloud-example, cache=false in Redis
Exception in thread "main" com.nepxion.permission.exception.PermissionException: No permision to proceed method [name=doB, parameterTypes=java.lang.String,java.lang.String], permissionName=B-Permission, permissionLabel=B权限
	at com.nepxion.permission.aop.PermissionInterceptor.invokePermission(PermissionInterceptor.java:131)
	at com.nepxion.permission.aop.PermissionInterceptor.invoke(PermissionInterceptor.java:73)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)
	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:213)
	at com.sun.proxy.$Proxy64.doB(Unknown Source)
	at com.nepxion.permission.MyApplication.main(MyApplication.java:31)

``` 