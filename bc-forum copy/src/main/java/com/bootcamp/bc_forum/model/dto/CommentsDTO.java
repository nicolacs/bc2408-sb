package com.bootcamp.bc_forum.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class CommentsDTO {
    private Integer postId;
    private Integer id;
    private String name;
    private String email;
    private String body;
}
