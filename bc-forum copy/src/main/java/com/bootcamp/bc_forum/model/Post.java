package com.bootcamp.bc_forum.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Post {
  private Integer userId;
  private Integer id;
  private String title;
  private String body;
    
}
