package com.tghd.automaticmanager.demo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {
    @RequestMapping(value="/hello/{username}",method = RequestMethod.GET)
    public String getUser(@PathVariable String username){
        return "hello " + username;
    }


}
