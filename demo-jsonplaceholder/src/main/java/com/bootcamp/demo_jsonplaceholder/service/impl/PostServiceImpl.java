package com.bootcamp.demo_jsonplaceholder.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.demo_jsonplaceholder.entity.PostEntity;
import com.bootcamp.demo_jsonplaceholder.repository.PostRepository;
import com.bootcamp.demo_jsonplaceholder.service.PostService;

@Service
public class PostServiceImpl implements PostService{
  @Autowired
  private PostRepository postRepository;

  @Override
  public List<PostEntity> saveAll(List<PostEntity> postEntities) {
    return postRepository.saveAll(postEntities);
  }

    
}
