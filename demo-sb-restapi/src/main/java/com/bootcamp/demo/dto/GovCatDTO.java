package com.bootcamp.demo.dto;

import com.bootcamp.demo.dto.CatDTO.Tail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class GovCatDTO {
  private TailDTO tail;


@Getter
@AllArgsConstructor
public static class TailDTO{
  private double tailLength;
}
}