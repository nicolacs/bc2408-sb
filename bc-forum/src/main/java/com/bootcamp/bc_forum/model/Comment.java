package com.bootcamp.bc_forum.model;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Setter
@Builder
public class Comment {
  private Long postId;
  private Long id;
  private String name;
  private String email;
  private String body;
}
