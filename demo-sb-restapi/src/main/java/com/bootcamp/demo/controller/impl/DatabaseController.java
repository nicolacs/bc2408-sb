package com.bootcamp.demo.controller.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.controller.DatabaseOperation;
import com.bootcamp.demo.model.Database;


// @Controller
// @ResponseBody // this is JSON format
@RestController //!!! = @Controller + @ResponseBody
public class DatabaseController implements DatabaseOperation{
    // APIs
    // 1. int put(int index, Integer integer);
    // 2. int get(int index)
    // 3. List<Integer> getAll()

    //1.
    public int put( int index, Integer integer){
        if (index < 0 || index > Database.integers.length -1)
            return -1;
        Database.integers[index] = integer;
        return integer;
    }

    //2.
    public int get(int index) {
        return Database.integers[index];
    }

    //3.
    public List<Integer> getAll(){
        return Arrays.stream(Database.integers)
            .boxed()
            .collect(Collectors.toList());
    }
  }

