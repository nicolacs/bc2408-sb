package com.bootcamp.demo_jsonplaceholder.model;

import lombok.Getter;

@Getter
public class PostDTO {
    private Long id;
    private Long userId;
    private String title;
    private String body;
}

