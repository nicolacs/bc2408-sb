package com.bootcamp.bc_forum.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Post {
  private Long userId;
  private Long id;
  private String title;
  private String body;
    
}
