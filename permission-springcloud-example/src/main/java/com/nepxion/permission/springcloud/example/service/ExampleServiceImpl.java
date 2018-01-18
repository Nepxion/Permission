package com.nepxion.permission.springcloud.example.service;

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

@Service
public class ExampleServiceImpl implements ExampleService {
    private static final Logger LOG = LoggerFactory.getLogger(ExampleServiceImpl.class);

    @Override
    public int doA(String userId, String userType, String value) {
        LOG.info("===== doA被调用");

        return 123;
    }

    @Override
    public String doB(String userId, String value) {
        LOG.info("----- doB被调用");
        
        return "abc";
    }
}