package com.bootcamp.demo.controller.impl;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bootcamp.demo.dto.HangSengCatDTO;
import com.bootcamp.demo.mapper.HangSengMapper;
import com.bootcamp.demo.model.Cat;
import com.bootcamp.demo.model.Color;


@Controller
@ResponseBody
public class HangSengCatController {
    // 1. return cat object ("John")
    // 2. return List of cat ("Peter", "Vincent")
    // 3. return a cat (path varible cat name)

    @GetMapping(value = "/cat")
    public Cat getCat() {
      return new Cat("John", 13, Color.RED, 4.5);
    }
    
   /// 2. return List of cat ("Peter", "Vincent")
    @GetMapping(value = "/cats")
    public List<HangSengCatDTO> getCats() {
      List<Cat> cats = List.of(new Cat("Vincent", 13, Color.RED, 13.0),
          new Cat("Peter", 10, Color.BLUE, 8));
      return HangSengMapper.map(cats);
    }
    


   // 
   // 3. return a cat (path variable cat name)
   // @GetMapping(value = "/cat/{name}")
   // public Cat getCat(@PathVariable String name) {
   // return new Cat(name);
   // }
   }

