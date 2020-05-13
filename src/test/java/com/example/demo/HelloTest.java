package com.example.demo;


import com.example.demo.controller.HelloController;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/*Spring 提供的测试工具启动时系统会将类自动加载到Spring Boot容器中*/
@SpringBootTest
public class HelloTest {

    private MockMvc mockMvc;

        /*Junit4 时注解为@Before Junit5为BeforeEach*/
        @BeforeEach
        public void setUp(){
            mockMvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
        }


    /**
     *
     * .andExpect(
     *      MockMvcResultMatchers.content()//获取Web请求后的结果
     *      .string(Matchers.containsString("bush"))//判断返回结果中是否包含字符串"xuke" 不包含则会抛出异常包含则正常运行
     *      )
     *
     * @throws Exception
     */
    @Test
        public void hello() throws Exception {


            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/getUser")//构造一个请求
                    //.param("name","xuke")//设置传值params()为多个
                    .accept(MediaType.APPLICATION_JSON_UTF8))//设置返回类型
                    .andDo(MockMvcResultHandlers.print())//输出整个响应结果信息
                    .andReturn();//执行完成后返回相应的结果
        }


}
