package com.bootcamp.bc_forum.service;

import java.util.List;
import com.bootcamp.bc_forum.entity.PostEntity;
import com.bootcamp.bc_forum.model.dto.PostsDTO;

public interface PostService {
    List<PostsDTO> getPost();
    List<PostEntity> saveAll(List<PostEntity> postEntities);
}
