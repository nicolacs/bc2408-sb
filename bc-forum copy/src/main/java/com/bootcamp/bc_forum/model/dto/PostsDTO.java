package com.bootcamp.bc_forum.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class PostsDTO {
    private Integer userId; //
    private Integer id;
    private String title;
    private String body;
    //List<Comments> comments = new ArrayList<>();
}
