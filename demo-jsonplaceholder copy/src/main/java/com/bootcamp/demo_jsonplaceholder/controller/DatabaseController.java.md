package com.bootcamp.demo_jsonplaceholder.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.hibernate.boot.model.relational.Database;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo_jsonplaceholder.controller.impl.DatabaseOperation;

// @Controller
// @ResponseBody // JSON format
@RestController // @Controller + @ResponseBody
public class DatabaseController implements DatabaseOperation {
  
  // APIs
  // 1. int put(int index, int integer)
  public int put(int index, int value) {
    if (index < 0 || index > Database.integer.length - 1)
      return -1;
    Database.integers[index] = value;
    return value;
  }

  // 2. int get(int index)
  public int get(int index) {
    return Database.integers[index];
  }

  // 3. List<Integer> getAll()
  public List<Integer> getAll() {
    return Arrays.stream(Database.integers) //
        .boxed() //
        .collect(Collectors.toList());
  }

@Override
public int get2(int index) {
    throw new UnsupportedOperationException("Unimplemented method 'get2'");
}
    
}
