package com.nepxion.permission.example.feign;

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

@FeignClient(value = "permission-springcloud-client-example")
public interface MyFeign {
    @RequestMapping(path = "/doA/{userId}/{userType}/{value}", method = RequestMethod.GET)
    int doA(@PathVariable(value = "userId") String userId, @PathVariable(value = "userType") String userType, @PathVariable(value = "value") String value);

    @RequestMapping(path = "/doB/{token}/{value}", method = RequestMethod.GET)
    String doB(@PathVariable(value = "token") String token, @PathVariable(value = "value") String value);

    @RequestMapping(path = "/doC/{value}", method = RequestMethod.GET)
    boolean doC(@PathVariable(value = "value") String value);
}