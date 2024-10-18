package com.bootcamp.demo_jsonplaceholder.model;

import lombok.Getter;

@Getter
public class CommentDTO {
  private Long id;
  private Long postId;
  private String body;
  private String email;
  private String name;
}
