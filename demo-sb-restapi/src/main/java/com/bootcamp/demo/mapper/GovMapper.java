package com.bootcamp.demo.mapper;

import java.util.List;
import java.util.stream.Collectors;
import com.bootcamp.demo.dto.GovCatDTO;
import com.bootcamp.demo.model.Cat;

public class GovMapper {
    public static List<GovCatDTO> govmap (List<Cat> cats){
        return cats.stream()
        .map(cat -> {
            return GovCatDTO.builder()
            .tail(new GovCatDTO.TailDTO(cat.getTailLength()))
            .build();
        }).collect(Collectors.toList());
        
    }
}
