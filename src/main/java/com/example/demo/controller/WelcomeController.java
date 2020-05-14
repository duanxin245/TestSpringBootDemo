package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
public class WelcomeController {

    @GetMapping("/")
    public String index(ModelMap map){
        map.addAttribute("message","blog");
        List<String> list = new ArrayList<String>(){{add("1");add("2");add("3");}};
        map.addAttribute("list",list);
        map.addAttribute("name","xuke");
        map.addAttribute("avg","10");
        Set<String> set = new HashSet<String>();
    return "TestThymeleaf";
    }

    @RequestMapping("/example")
    public String example(ModelMap map){
        map.addAttribute("uuid",UUID.randomUUID().toString().replaceAll("-",""));
        return "chat_example";
    }

}
