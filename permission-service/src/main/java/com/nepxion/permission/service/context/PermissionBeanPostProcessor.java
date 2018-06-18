package com.nepxion.permission.service.context;

/**
 * <p>Title: Nepxion Permission</p>
 * <p>Description: Nepxion Permission</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.alibaba.druid.pool.DruidDataSource;

public class PermissionBeanPostProcessor implements BeanPostProcessor {
    private static final Logger LOG = LoggerFactory.getLogger(PermissionBeanPostProcessor.class);
    private static final String DATA_SOURCE_NAME = "druidDataSource";

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof DruidDataSource && StringUtils.equals(beanName, DATA_SOURCE_NAME)) {
            DruidDataSource dataSource = (DruidDataSource) bean;

            // 如果服务跟数据库连接异常，则直接退出服务进程
            try {
                dataSource.getConnection();
            } catch (SQLException e) {
                LOG.error("Datasource get connection failed", e);
                System.exit(1);
            }
        }

        return bean;
    }
}