package com.bootcamp.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import com.bootcamp.demo.model.UserDTO;

public interface JPHOperation {
    @GetMapping("/jph/users")
    List<UserDTO> getUsers();
  }
