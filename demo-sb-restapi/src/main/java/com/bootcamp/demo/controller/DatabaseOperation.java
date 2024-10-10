package com.bootcamp.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface DatabaseOperation {
    //1.
    @GetMapping(value = "/integers/intput/{index}/{integer}")
    public int put(@PathVariable int index, @PathVariable Integer value);

    //2.
    @GetMapping("/integers/get/{index}")
    public int get(int index) ;
    

    //3.
    @GetMapping("/integers/getall")
    public List<Integer> getAll();   
}
