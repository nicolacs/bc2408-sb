package com.bootcamp.bc_forum.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Data
@Builder
@Setter
@NoArgsConstructor
public class CommentsDTO {
    private Long postId;
    private Long id;
    private String name;
    private String email;
    private String body;
}
