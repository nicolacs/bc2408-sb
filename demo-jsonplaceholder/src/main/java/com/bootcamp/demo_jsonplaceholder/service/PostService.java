package com.bootcamp.demo_jsonplaceholder.service;

import java.util.List;
import com.bootcamp.demo_jsonplaceholder.entity.PostEntity;

public interface PostService {
    List<PostEntity> saveAll(List<PostEntity> postEntities);
}
