package com.nepxion.permission.service.impl;

/**
 * <p>Title: Nepxion Permission</p>
 * <p>Description: Nepxion Permission</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nepxion.permission.api.UserResource;
import com.nepxion.permission.entity.UserEntity;

@RestController
public class UserResourceImpl implements UserResource {
    private static final Logger LOG = LoggerFactory.getLogger(UserResourceImpl.class);

    @Override
    public UserEntity getUser(@PathVariable(value = "token") String token) {
        LOG.info("Token：{}", token);
        if (StringUtils.equals(token, "abcd1234")) {
            UserEntity user = new UserEntity();
            user.setUserId("lisi");
            user.setUserType("LDAP");

            return user;
        }

        return null;
    }
}