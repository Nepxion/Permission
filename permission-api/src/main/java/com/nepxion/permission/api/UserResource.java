package com.nepxion.permission.api;

/**
 * <p>Title: Nepxion Permission</p>
 * <p>Description: Nepxion Permission</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nepxion.permission.entity.UserEntity;

@FeignClient(value = "${permission.service.name}")
public interface UserResource {
    @RequestMapping(path = "/user/getUser/{token}", method = RequestMethod.GET)
    UserEntity getUser(@PathVariable(value = "token") String token);
}