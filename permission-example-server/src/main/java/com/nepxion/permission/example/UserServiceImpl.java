package com.nepxion.permission.example;

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
import org.springframework.stereotype.Service;

import com.nepxion.permission.annotation.UserId;
import com.nepxion.permission.annotation.UserType;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public boolean addUser(@UserId String userId, @UserType String userType, String user) {
        LOG.info("========== 添加用户");

        return true;
    }

    @Override
    public boolean deleteUser(@UserId String userId, @UserType String userType, String user, String org) {
        LOG.info("========== 删除用户");

        return true;
    }
}