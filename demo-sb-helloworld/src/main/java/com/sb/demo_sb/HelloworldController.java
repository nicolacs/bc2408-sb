package com.sb.demo_sb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class HelloworldController {
    
    //endpoint to locate the web service
    // http://localhost:8080/hello
    // same as http://127.0.0.1:8080/hello
    @GetMapping(value = "/hello")
    public String sayHello(){
        return "Hello World!ssss";
    }
}
