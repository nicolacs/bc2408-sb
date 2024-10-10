package com.bootcamp.demo.service;

// https://jsonplaceholder.typicode.com/users
import java.util.List;
import com.bootcamp.demo.model.UserDTO;

public interface JPHService {
  List<UserDTO> getUsers();
}
