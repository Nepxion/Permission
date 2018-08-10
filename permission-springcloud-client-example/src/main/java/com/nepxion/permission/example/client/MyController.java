package com.nepxion.permission.example.client;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nepxion.permission.annotation.Permission;
import com.nepxion.permission.annotation.Token;
import com.nepxion.permission.annotation.UserId;
import com.nepxion.permission.annotation.UserType;

@RestController
public class MyController {
    private static final Logger LOG = LoggerFactory.getLogger(MyController.class);

    // 显式基于UserId和UserType的权限验证，参数通过注解传递
    @RequestMapping(path = "/doA/{userId}/{userType}/{value}", method = RequestMethod.GET)
    @Permission(name = "A-Permission", label = "A权限", description = "A权限的描述")
    public int doA(@PathVariable(value = "userId") @UserId String userId, @PathVariable(value = "userType") @UserType String userType, @PathVariable(value = "value") String value) {
        LOG.info("===== doA被调用");

        return 123;
    }

    // 显式基于Token的权限验证，参数通过注解传递
    @RequestMapping(path = "/doB/{token}/{value}", method = RequestMethod.GET)
    @Permission(name = "B-Permission", label = "B权限", description = "B权限的描述")
    public String doB(@PathVariable(value = "token") @Token String token, @PathVariable(value = "value") String value) {
        LOG.info("----- doB被调用");

        return "abc";
    }

    // 隐式基于Rest请求的权限验证，参数通过Header传递
    @RequestMapping(path = "/doC/{value}", method = RequestMethod.GET)
    @Permission(name = "C-Permission", label = "C权限", description = "C权限的描述")
    public boolean doC(@PathVariable(value = "value") String value) {
        LOG.info("----- doC被调用");

        return true;
    }
}