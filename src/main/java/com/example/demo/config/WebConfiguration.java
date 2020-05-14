package com.example.demo.config;

import com.example.demo.filter.MyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration {
    @Bean
    public FilterRegistrationBean testFilterRegistration(){
            FilterRegistrationBean registration = new FilterRegistrationBean();
            registration.setFilter(new MyFilter());
            registration.addUrlPatterns("/*");
            registration.setName("MyFilter");
            registration.setOrder(6);//过滤器执行顺序
            return registration;
    }


}
