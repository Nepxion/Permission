package com.nepxion.permission.example.client;

/**
 * <p>Title: Nepxion Permission</p>
 * <p>Description: Nepxion Permission</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @Autowired
    private MyFeign myFeign;

    @RequestMapping(path = "/doA/{userId}/{userType}/{value}", method = RequestMethod.GET)
    public int doA(@PathVariable(value = "userId") String userId, @PathVariable(value = "userType") String userType, @PathVariable(value = "value") String value) {
        return myFeign.doA(userId, userType, value);
    }

    @RequestMapping(path = "/doB/{token}/{value}", method = RequestMethod.GET)
    public String doB(@PathVariable(value = "token") String token, @PathVariable(value = "value") String value) {
        return myFeign.doB(token, value);
    }

    @RequestMapping(path = "/doC/{value}", method = RequestMethod.GET)
    public boolean doC(@PathVariable(value = "value") String value) {
        return myFeign.doC(value);
    }
}