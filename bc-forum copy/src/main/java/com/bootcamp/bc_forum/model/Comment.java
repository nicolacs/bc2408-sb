package com.bootcamp.bc_forum.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Comment {
  private Integer postId;
  private Integer id;
  private String name;
  private String email;
  private String body;
}
