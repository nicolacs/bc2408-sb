package com.bootcamp.demo.mapper;

import java.util.List;
import java.util.stream.Collectors;
import com.bootcamp.demo.dto.HangSengCatDTO;
import com.bootcamp.demo.model.Cat;

public class HangSengMapper {

    // def map(abc)
    // def map(ijk)

  public static List<HangSengCatDTO> map(List<Cat> cats) {
    return cats.stream() //
        .map(cat -> {
          HangSengCatDTO.Eye[] eyes = new HangSengCatDTO.Eye[] {
              new HangSengCatDTO.Eye(cat.getLeftEye().getColor()),
              new HangSengCatDTO.Eye(cat.getRightEye().getColor())};
          return HangSengCatDTO.builder() //
              .name(cat.getName()) //
              .age(cat.getAge()) //
              .eyes(eyes) //
              .tail(new HangSengCatDTO.Tail(cat.getTailLength())) //
              .build();
        }).collect(Collectors.toList());
  }
}