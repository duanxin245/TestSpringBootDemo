package com.example.demo;

import com.example.demo.model.Emil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class DemoSpringBootApplicationTests {
    @Resource
    private Emil emil;


    @Test
    void contextLoads() {
        System.out.println("host："+emil.getHost());
        System.out.println("uname："+emil.getUserName());
    }

}
