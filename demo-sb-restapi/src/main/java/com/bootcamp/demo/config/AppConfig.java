package com.bootcamp.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.demo.model.Cat;
import com.bootcamp.demo.model.Color;

// Component Scan -> @Controller, @Service, @Configuration
// 組合技: @Configuration + @Bean

@Configuration
public class AppConfig { // this is bean
    @Bean(name = "JPHRestTemplate")
    RestTemplate restTemplateForJPH(){
        return new RestTemplate();
    }

    @Bean(name = "ABCRestTemplate")
    RestTemplate restTemplateForABC(){
        return new RestTemplate();
    }

    @Bean
    Cat cat() {
    return new Cat("Vincent", 20, Color.BLUE, 3.0);
  }
    
}
