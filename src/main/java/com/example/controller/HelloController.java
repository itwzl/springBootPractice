package com.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Company: www.leadmap.net
 * Description:
 *
 * @author: 49022
 * @Date: 2019/1/14 15:50
 */
@RestController
public class HelloController {

    @Value("${name}")
    private String name;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String say() {
        return "hello springboot!" + ":" + name;
    }
}
