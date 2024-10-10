package com.bootcamp.demo_jsonplaceholder.controller.impl;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface DatabaseOperation {
    @PostMapping("/integers/{index}/{value}")
    public int put(@PathVariable int index, @PathVariable int value);
  
    // 
    @GetMapping("/integers/{index}")
    public int get(@PathVariable int index);

    //
    @GetMapping("/integers")
    public int get2(@RequestParam(value = "idx") int index);
  
    @GetMapping("/integers/getall")
    public List<Integer> getAll();
    
    // @DeleteMapping -> SQL: delete from
    // @....
}
