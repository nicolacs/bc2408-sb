package com.bootcamp.demo.controller.impl;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.DemoSbRestapiApplication;
import com.bootcamp.demo.controller.BeanOperation;

@RestController
public class BeanController implements BeanOperation {

    // TRY obj in bean
    private int x = 10;

    @Override
    public int getX(){
        return this.x;
    }

     @Override
  public List<String> getBeans() {
    return List.of(DemoSbRestapiApplication.context.getBeanDefinitionNames());
  }
  // List.of(), new ArrayList<>(), Arrays.asList()
    
}
