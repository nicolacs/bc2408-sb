package com.bootcamp.demo.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.controller.JPHOperation;
import com.bootcamp.demo.model.UserDTO;
import com.bootcamp.demo.service.JPHService;
import com.bootcamp.demo.service.JPHServiceImpl;

// A new Thread
  // JPHController c = get bean from Context
  // c.getUsers();

@RestController
public class JPHController implements JPHOperation {
  private static String x = "abc";
  // Controller Layer -> Service Layer
  // !!! Autowired (Bean Injection - from Spring Context)
    // Before Server start:
      // Spring check if there is a bean from Spring Context can be injected into this object reference
      // What if not found ? Server start fail ....
    // After Server start:
      // API request comes... jphController Bean calls jphService Bean
    //@Autowised(required = false)
      // If the bean not found, still proceed server starts...

  @Autowired //!!! 係垃圾桶攞返粒豆(@Service) 黎隻OBJ出黎俾Controller用
  private JPHService jphService; // Interface
  // 好似 Animal animal = new Cat(); 既情況

  @Override
  public List<UserDTO> getUsers() {
    //return new JPHServiceImpl().getUsers();
    return jphService.getUsers();
  }
}


