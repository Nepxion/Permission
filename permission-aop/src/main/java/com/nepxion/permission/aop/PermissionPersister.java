package com.nepxion.permission.aop;

/**
 * <p>Title: Nepxion Permission</p>
 * <p>Description: Nepxion Permission</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;

import com.nepxion.permission.api.PermissionResource;
import com.nepxion.permission.constant.PermissionConstant;
import com.nepxion.permission.entity.PermissionEntity;

public class PermissionPersister implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger LOG = LoggerFactory.getLogger(PermissionPersister.class);

    @Value("${" + PermissionConstant.PERMISSION_AUTOMATIC_PERSIST_ENABLED + ":true}")
    private Boolean automaticPersistEnabled;

    @Value("${" + PermissionConstant.PERMISSION_AUTOMATIC_PERSIST_RETRY_TIMES + ":5}")
    private Integer automaticPersistRetryTimes;

    @Value("${" + PermissionConstant.PERMISSION_AUTOMATIC_PERSIST_RETRY_INTERVAL + ":10000}")
    private Long automaticPersistRetryInterval;

    @Autowired
    private PermissionAutoScanProxy permissionAutoScanProxy;

    @Autowired
    private PermissionResource permissionResource;

    @PostConstruct
    public void initialize() {
        LOG.info("Permission automatic persist enabled is {}...", automaticPersistEnabled);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (automaticPersistEnabled) {
            if (event.getApplicationContext().getParent() instanceof AnnotationConfigApplicationContext) {
                LOG.info("Start to persist with following permission list...");
                LOG.info("------------------------------------------------------------");
                List<PermissionEntity> permissions = permissionAutoScanProxy.getPermissions();
                if (CollectionUtils.isNotEmpty(permissions)) {
                    for (PermissionEntity permission : permissions) {
                        LOG.info("Permission={}", permission);
                    }

                    persist(permissions, automaticPersistRetryTimes + 1);
                } else {
                    LOG.warn("Permission list is empty");
                }
                LOG.info("------------------------------------------------------------");
            }
        }
    }

    private void persist(List<PermissionEntity> permissions, int forceTimes) {
        try {
            permissionResource.persist(permissions);

            LOG.info("Permissions persist successfully");
        } catch (Exception e) {
            LOG.error("Permissions persist failed", e);

            forceTimes--;
            if (forceTimes > 0) {
                LOG.info("Try {} times for Permissions persist after {} ms", forceTimes, automaticPersistRetryInterval);

                try {
                    Thread.sleep(automaticPersistRetryInterval);
                } catch (InterruptedException ex) {

                }

                persist(permissions, forceTimes);
            }
        }
    }
}