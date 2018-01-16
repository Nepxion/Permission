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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.nepxion.permission.PermissionDelegate;
import com.nepxion.permission.constant.PermissionConstant;
import com.nepxion.permission.entity.PermissionEntity;

@Component("permissionScanner")
public class PermissionPersister implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger LOG = LoggerFactory.getLogger(PermissionPersister.class);

    @Value("${" + PermissionConstant.PERMISSION_AUTOMATIC_PERSIST_ENABLED + ":true}")
    private Boolean automaticPersistEnabled;

    @Autowired
    private PermissionAutoScanProxy permissionAutoScanProxy;

    @Autowired
    private PermissionDelegate permissionDelegate;

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
                List<PermissionEntity> permissionEntityList = permissionAutoScanProxy.getPermissionEntityList();
                for (PermissionEntity permissionEntity : permissionEntityList) {
                    LOG.info("PermissionEntity={}", permissionEntity);
                }
                LOG.info("------------------------------------------------------------");

                permissionDelegate.persist(permissionEntityList);
            }
        }
    }
}