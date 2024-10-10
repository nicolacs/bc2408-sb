package com.bootcamp.demo.dto;

import com.bootcamp.demo.model.Color;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

// DTO (Data Transfer Object), represents JSON format
@Getter
@AllArgsConstructor
@Builder
public class CatDTO {
  private String name;
  private int age;
  private Eye[] eyes;
  private Tail tail;



@Getter
@AllArgsConstructor
public static class Eye{
    private Color color;
}

@Getter
@AllArgsConstructor
public static class Tail{
    private double length;
}  
}