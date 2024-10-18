package com.bootcamp.demo_jsonplaceholder.config;

// package com.bootcamp.demo_jsonplacehlder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

// Component Scan -> @Controller, @Service, @Configuration
// 組合技: @Configuration + @Bean

@Configuration
public class AppConfig { // this is bean

// ! Usually we use "@Cofiguration + @Bean", when we use external library/class

    @Bean(name = "JPHRestTemplate")
    RestTemplate restTemplateForJPH(){
        return new RestTemplate(); // spring web
    }

    @Bean(name = "ABCRestTemplate")
    RestTemplate restTemplateForABC(){
        return new RestTemplate();
    }

}
