package com.example.demo.controller;

import org.assertj.core.util.Lists;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

}
