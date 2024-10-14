package com.bootcamp.bc_forum.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class CommentsDTO {
    private int id;
    private String name;
    private String email;
    private String body;
}
