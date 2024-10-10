package com.bootcamp.demo.dto;

import com.bootcamp.demo.model.Color;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// DTO (Data Transfer Object), represents JSON format
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HangSengCatDTO {
  private String name;
  private int age;
  private Eye[] eyes;
  private Tail tail;

  @Getter
  @AllArgsConstructor
  public static class Eye {
    private Color color;
  }

  @Getter
  @AllArgsConstructor
  public static class Tail {
    private double length;
  }
}
