package com.example.demo.controller;

import com.example.demo.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/*@RestController 注解表示返回内容都为JSON格式输出同等于SpringMVC的  @ResponseBody+@Controller   */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(String name){
        return "hello world, "+name;
    }

    @RequestMapping(value = "/getUser",method = RequestMethod.GET)
    public User getUser(){
        User user = new User();
        user.setName("li");
        user.setAge(21);
        user.setPass("123456789");
        return user;
    }

}
