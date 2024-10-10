package com.bootcamp.demo.controller.impl;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bootcamp.demo.dto.GovCatDTO;
import com.bootcamp.demo.mapper.GovMapper;
import com.bootcamp.demo.model.Cat;
import com.bootcamp.demo.model.Color;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@ResponseBody
public class GovCatController {
  // Consideration: URL design (based on api consumer and resources)
  // return List<GovCatDTO>
  @GetMapping(value = "/govcat")
  public List<GovCatDTO> getGovCat(){
    List<Cat> cats = List.of(new Cat("Vincent", 13, Color.RED, 13.0),
          new Cat("Peter", 10, Color.BLUE, 8));
      return GovMapper.govmap(cats);
  }

  @GetMapping(value = "/govcatsss")
  public List<GovCatDTO> getGovCatsss(){
    List<Cat> cats = List.of(new Cat("Vincent", 13, Color.RED, 13.0),
    new Cat("Peter", 10, Color.BLUE, 8), new Cat("Sally", 8, Color.BLUE, 24.0));
    return GovMapper.govmap(cats);
  }
  
  
}
